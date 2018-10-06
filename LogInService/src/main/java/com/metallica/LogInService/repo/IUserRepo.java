package com.metallica.LogInService.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metallica.LogInService.entity.User;

public interface IUserRepo extends JpaRepository<User, Integer>{

}
