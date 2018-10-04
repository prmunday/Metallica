package com.metallica.tradingService.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.metallica.tradingService.entities.TradingEntity;

public interface ITradingRepo extends JpaRepository<TradingEntity, Integer>,
JpaSpecificationExecutor<TradingEntity> {



}
