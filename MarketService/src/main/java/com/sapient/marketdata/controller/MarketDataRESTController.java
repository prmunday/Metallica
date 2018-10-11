package com.sapient.marketdata.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.marketdata.entity.MarketDataEntity;
import com.sapient.marketdata.repo.IMarketDataRepo;

@RestController
@CrossOrigin("*")
public class MarketDataRESTController {
	
	@Autowired
	IMarketDataRepo marketDataRepo;
	
	@RequestMapping(path="/data", method=RequestMethod.GET)
	public List<MarketDataEntity> fetchAllWorkoutCollections() {
		List<MarketDataEntity> marketData = marketDataRepo.findAll();
		for(MarketDataEntity m : marketData) {
			double randomPercent = (int)((Math.random()*((5.00-(-5.00))+1))+(-5.00))*.01;
			if(randomPercent<0) {
				m.setPositive(false);
			}else {
				m.setPositive(true);
			}
			double percentPrice = m.getPrice()*randomPercent;
			double newPrice = percentPrice+m.getPrice();			
			BigDecimal bd = new BigDecimal(newPrice);
			bd = bd.setScale(2, RoundingMode.HALF_UP);
			m.setPrice(bd.doubleValue());
			marketDataRepo.save(m);
		}
		return marketData;
	}
	
	@RequestMapping(path="/data", method=RequestMethod.POST)
	public void addMarketData() {
		List<MarketDataEntity> newMarketData = new ArrayList<MarketDataEntity>();
		List<MarketDataEntity> marketData = marketDataRepo.findAll();
		if(marketData.size()==0) {
			newMarketData.add(new MarketDataEntity("Gold", "GO", 200, true));
			newMarketData.add(new MarketDataEntity("Silver", "SL", 100, true));
			newMarketData.add(new MarketDataEntity("Palladium", "PA", 600, true));
			newMarketData.add(new MarketDataEntity("Aluminium", "AL", 700, true));
			newMarketData.add(new MarketDataEntity("Adamantium", "AD", 500, true));
			newMarketData.add(new MarketDataEntity("Uranium", "UR", 520, true));
			newMarketData.add(new MarketDataEntity("Vibranium", "VB", 700, true));
		}
		for(MarketDataEntity m : newMarketData) {
			marketDataRepo.save(m);
		}
	}
	
}
