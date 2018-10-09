package com.metallica.NewRefService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.metallica.NewRefService.entity.Commodity;
import com.metallica.NewRefService.entity.CounterParty;
import com.metallica.NewRefService.entity.Location;
import com.metallica.NewRefService.repos.ICommodityRepo;
import com.metallica.NewRefService.repos.ICounterPartyRepo;
import com.metallica.NewRefService.repos.ILocationRepo;

@RestController
@CrossOrigin("*")
public class RefController {
	
	@Autowired
	ICommodityRepo commodityrepo;
	
	@Autowired
	ICounterPartyRepo counterepo;
	
	@Autowired
	ILocationRepo locationrepo;
	
	@RequestMapping(path="/commodities", method=RequestMethod.GET)
	public List<Commodity> fetchAllCommodities(){
		List<Commodity> commodities = commodityrepo.findAll();
		return commodities;
	}
	
	@RequestMapping(path="/counterparties", method=RequestMethod.GET)
	public List<CounterParty> fetchAllCounterParties(){
		List<CounterParty> counterparties = counterepo.findAll();
		return counterparties;
	}
	
	@RequestMapping(path="/locations", method=RequestMethod.GET)
	public List<Location> fetchAllLocations(){
		List<Location> locations = locationrepo.findAll();
		return locations;
	}
	
	
	@RequestMapping(path="/add", method=RequestMethod.POST)
	public void Add(){
//		Location loc=new Location("Raipur");
//		Location loc1=new Location("Bhilai");
//		Location loc2=new Location("Jamshedpur");
//		Location loc3=new Location("Dearborn");
//		Location loc4=new Location("East Chicago");
//		locationrepo.save(loc);
//		locationrepo.save(loc1);
//		locationrepo.save(loc2);
//		locationrepo.save(loc3);
//		locationrepo.save(loc4);
		
		List<String> counterIdlist=new ArrayList<String>();
		counterIdlist.add("BALCO");
		counterIdlist.add("GMDC");
		counterIdlist.add("NALCO");
		counterIdlist.add("NAAC");
		counterIdlist.add("QAC");
		List<String> counterNamelist=new ArrayList<String>();
		counterNamelist.add("Bharat Aluminium Company");
		counterNamelist.add("Gujarat Mineral development corporation");
		counterNamelist.add("National Aluminium Company");
		counterNamelist.add("Nippon Amazon Aluminium corporation");
		counterNamelist.add("Queensland Alumina Limited");
		for(int i =0;i<counterIdlist.size();i++) {
			CounterParty cp=new CounterParty(counterIdlist.get(i),counterNamelist.get(i));
			counterepo.save(cp);
		}
		
		
		List<String> commodityIdlist=new ArrayList<String>();
		commodityIdlist.add("Al");
		commodityIdlist.add("QS");
		commodityIdlist.add("HG");
		commodityIdlist.add("GC");
		commodityIdlist.add("SI");
		commodityIdlist.add("PL");
		List<String> commodityNamelist=new ArrayList<String>();
		commodityNamelist.add("Aluminium");
		commodityNamelist.add("Asian Gold");
		commodityNamelist.add("Copper");
		commodityNamelist.add("Gold");
		commodityNamelist.add("Silver futures");
		commodityNamelist.add("Platinum Futures");
		for(int i =0;i<commodityIdlist.size();i++) {
			Commodity cd=new Commodity(commodityIdlist.get(i),commodityNamelist.get(i));
			commodityrepo.save(cd);
		}
		
		
		
		
	}

}
