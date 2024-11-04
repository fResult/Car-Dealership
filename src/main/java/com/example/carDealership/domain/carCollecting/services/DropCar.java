package com.example.carDealership.domain.carCollecting.services;

import com.example.carDealership.domain.carCollecting.entities.CarCollection;
import com.example.carDealership.domain.warehouse.Stock;

public class DropCar {
    public void operate(CarCollection carCollection, Stock stock) {
        carCollection.carDroppedToWarehouse();
        stock.incrementStockQuantity(1);
    }
}
