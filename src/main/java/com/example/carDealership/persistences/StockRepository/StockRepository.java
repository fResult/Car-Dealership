package com.example.carDealership.persistences.StockRepository;

import com.example.carDealership.domain.warehouse.Stock;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Long> {
    List<Stock> findByModel(String model);
}
