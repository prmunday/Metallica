package com.metallica.NewRefService.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metallica.NewRefService.entity.Commodity;


public interface ICommodityRepo extends JpaRepository<Commodity, Integer>{
	public Commodity findById(int id);

}
