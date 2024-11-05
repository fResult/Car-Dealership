package com.example.carDealership.domains;

import com.example.carDealership.domains.carCollecting.entities.CarCollection;
import com.example.carDealership.domains.warehouse.Stock;

public interface CarCollectingRepository {
    CarCollection saveCarCollection(CarCollection entity);
    Stock saveStock(Stock entity);

    CarCollection findCarCollectionById(long id);
    Stock findStockByModel(String model);
}
