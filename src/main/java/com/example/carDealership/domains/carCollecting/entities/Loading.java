package com.example.carDealership.domains.carCollecting.entities;

import com.example.carDealership.domains.CarCollectingRepository;
import com.example.carDealership.domains.CarCollectingRepository.CarCollectingRepository;
import com.example.carDealership.domains.validations.ValidattionException;
import com.example.carDealership.domains.warehouse.Stock;

import java.util.Date;

// From the Loading form for car dropping at warehouse
public class Loading {
    private CarCollection relatedCarCollection;
    private Stock relatedStock;

    private long id;
    private String receivedEmployeeId;
    private Date timestamp;
    private String comment;

    public Loading(CarCollectingRepository carCollectionRepository) {

    }

    public long getId() {
        return id;
    }

    public String getReceivedEmployeeId() {
        return receivedEmployeeId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getComment() {
        return comment;
    }

    // And other related fields in the form
    public void droppedCarAtWarehouse() throws ValidattionException {
        // Do something with its own entity
        // Such as validation, update things, etc.

        relatedCarCollection.carDroppedToWarehouse();
        relatedStock.incrementStockQuantity(1);


    }
}
