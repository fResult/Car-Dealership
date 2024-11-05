package com.example.carDealership.persistences;

import com.example.carDealership.domains.warehouse.Stock;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Long> {
    List<Stock> findByModel(String model);
}
