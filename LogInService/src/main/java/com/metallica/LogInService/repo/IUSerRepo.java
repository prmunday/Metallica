package com.metallica.LogInService.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metallica.LogInService.entity.User;

public interface IUSerRepo extends JpaRepository<User, Integer>{

}
