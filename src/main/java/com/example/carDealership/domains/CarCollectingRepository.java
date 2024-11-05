package com.example.carDealership.domains;

import com.example.carDealership.domains.carCollecting.entities.CarCollection;
import com.example.carDealership.domains.warehouse.Stock;

import java.util.List;
import java.util.Optional;

public interface CarCollectingRepository {
    CarCollection saveCarCollection(CarCollection entity);
    Stock saveStock(Stock entity);

    Optional<CarCollection> findCarCollectionById(long id);
    List<Stock> findStocksByModel(String model);
}
