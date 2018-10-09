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
@RequestMapping("/marketdata")
@CrossOrigin("*")
public class MarketDataRESTController {
	
	@Autowired
	IMarketDataRepo marketDataRepo;
	
	@RequestMapping(path="/data", method=RequestMethod.GET)
	public List<MarketDataEntity> fetchAllWorkoutCollections() {
		List<MarketDataEntity> marketData = marketDataRepo.findAll();
		for(MarketDataEntity m : marketData) {
			double randomPercent = (int)((Math.random()*((5.00-(-5.00))+1))+(-5.00))*.01;
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
			newMarketData.add(new MarketDataEntity("Apple", "APPL", 200));
			newMarketData.add(new MarketDataEntity("Facebook", "FB", 100));
			newMarketData.add(new MarketDataEntity("Oracle", "ORCL", 600));
			newMarketData.add(new MarketDataEntity("Publicis", "PUB", 700));
		}
		for(MarketDataEntity m : newMarketData) {
			marketDataRepo.save(m);
		}
	}
	
}
