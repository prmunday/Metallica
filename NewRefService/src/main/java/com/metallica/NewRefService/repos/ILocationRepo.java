package com.metallica.NewRefService.repos;

import org.springframework.data.jpa.repository.JpaRepository;


import com.metallica.NewRefService.entity.Location;

public interface ILocationRepo extends JpaRepository<Location, Integer>{
	public Location findById(int id);
}
