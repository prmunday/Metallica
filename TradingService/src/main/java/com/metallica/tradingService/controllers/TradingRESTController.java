package com.metallica.tradingService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/trade")
@CrossOrigin("*")
public class TradingRESTController {
	
	@Autowired
	ITradingRepo tradingRepo;
	
	
	/*******************************METHODS***********************************/
	/**adds new Trading Entity to trading data table.
	 * 
	 * @param newTrade
	 */
	@RequestMapping(path="/add", method=RequestMethod.POST)
	public void addTrade(@RequestBody TradingEntity newTrade){
		tradingRepo.save(newTrade);
	}
	
	/**delete the trade order that corresponds to the id inputed if and 
	 * only if the trade status is open.
	 * 
	 * @param id
	 */
	@RequestMapping(path="/delete/{id}", method=RequestMethod.DELETE)
	public void deleteTrade(@PathVariable("id") int id){
		TradingEntity trade = getTrade(id);
		if(trade.getStatus() == TradeStatus.OPEN) {
			tradingRepo.deleteById(id);
		}
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
	
	/**find one trading entity that matches the id that was inputed.
	 * 
	 * @param id
	 * @return trading entity with trading_id equal to id
	 */
	@RequestMapping(path="/find/{id}", method=RequestMethod.GET)
	public TradingEntity getTrade(@PathVariable("id") int id){
		TradingEntity trade = tradingRepo.getOne(id);
		return trade;
	}
	
	/**search for list of trades that meets the search criteria
	 * 
	 * @param newTrade
	 */
	@RequestMapping(path="/search", method=RequestMethod.POST)
	public List<TradingEntity> searchTrades(@RequestBody SearchInfo search){
		
		List<TradingEntity> trades = tradingRepo.findAll(
				SearchInfo.searchCriteria(search));
		return trades;
		
	}
	
}
