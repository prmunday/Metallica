package com.sapient.marketdata.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sapient.marketdata.entity.MarketDataEntity;

public interface IMarketDataRepo extends JpaRepository<MarketDataEntity, Integer>{

}
