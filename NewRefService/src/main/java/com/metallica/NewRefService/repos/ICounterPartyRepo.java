package com.metallica.NewRefService.repos;

import org.springframework.data.jpa.repository.JpaRepository;


import com.metallica.NewRefService.entity.CounterParty;

public interface ICounterPartyRepo extends JpaRepository<CounterParty, Integer>{
	public CounterParty findById(int id);
}
