package com.metallica.tradingService.controllers;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.metallica.tradingService.entities.TradingEntity;
import com.metallica.tradingService.etc.SearchInfo;
import com.metallica.tradingService.etc.TradeStatus;
import com.metallica.tradingService.repos.ITradingRepo;
import com.metallica.tradingService.service.RabbitMQSender;

@RestController
@CrossOrigin("*")
public class TradingRESTController {

	@Autowired
	ITradingRepo tradingRepo;

	@Autowired
	RabbitMQSender rabbitMQSender;
	
	ExecutorService tradeHandlingThreadPool = Executors.newFixedThreadPool(4);
	
	public static int TRADE_STATUS_TEST_DELAY = 600000;


	/*******************************METHODS***********************************/
	/**adds new Trading Entity to trading data table.
	 * 
	 * @param newTrade
	 */
	@RequestMapping(path="/add", method=RequestMethod.POST)
	public void addTrade(@RequestBody TradingEntity newTrade) {
		newTrade.setStatus(TradeStatus.OPEN);
		tradingRepo.save(newTrade);
		tradeHandlingThreadPool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(TradingRESTController.TRADE_STATUS_TEST_DELAY);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				newTrade.setStatus(TradeStatus.CLOSED);
				tradingRepo.save(newTrade);
			}
			
		});
		rabbitMQSender.send(newTrade);
	}

	/**delete the trade order that corresponds to the id inputed if and 
	 * only if the trade status is open.
	 * 
	 * @param id
	 */
	@RequestMapping(path="/delete/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteTrade(@PathVariable("id") int id){
		TradingEntity trade = tradingRepo.getOne(id);
		if(trade.getStatus() == TradeStatus.OPEN) {
			tradingRepo.deleteById(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

	/**finds all the entries in the data table "trading_data"
	 * and returns them as a list.
	 * 
	 * @return list of trading entities
	 */
	@RequestMapping(path="/findAll", method=RequestMethod.GET)
	public List<TradingEntity> getAllTrades(){
		List<TradingEntity> trades = tradingRepo.findAll();
		return trades;
	}

	/**edit one trading entity that matches the tradeInfo
	 * that was inputed.
	 * 
	 * @param id
	 * 
	 */
	@RequestMapping(path="/edit", method=RequestMethod.PUT)
	public ResponseEntity<String> editTrade(@RequestBody TradingEntity tradeInfo){
		TradingEntity trade = tradingRepo.getOne(tradeInfo.getId());
		if(trade.getStatus() == TradeStatus.OPEN) {
			tradeInfo.setStatus(trade.getStatus());
			tradingRepo.save(tradeInfo);
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

	/**search for list of trades that meets the search criteria
	 * 
	 * @param newTrade
	 */
	@RequestMapping(path="/search", method=RequestMethod.POST)
	public ResponseEntity<List<TradingEntity>> searchTrades(
			@RequestBody SearchInfo search){
		if(search.validDate()) {
			List<TradingEntity> trades = tradingRepo.findAll(
					SearchInfo.searchCriteria(search));
			return new ResponseEntity<List<TradingEntity>>(trades,
					HttpStatus.OK);
		}
		
		return  new ResponseEntity<List<TradingEntity>>(
				HttpStatus.BAD_REQUEST);
	}

}
