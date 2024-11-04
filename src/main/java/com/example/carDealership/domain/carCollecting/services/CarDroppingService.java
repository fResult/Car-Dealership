package com.example.carDealership.domain.carCollecting.services;

import com.example.carDealership.domain.carCollecting.entities.CarCollection;
import com.example.carDealership.domain.warehouse.Stock;

public class CarDroppingService {
    public void dropCar(CarCollection carCollection, Stock stock) {
        // Service should update CarCollection.status in the Aggregate Root instead of update status directly
        carCollection.carDroppedToWarehouse();
        stock.incrementStockQuantity(1);
    }
}
