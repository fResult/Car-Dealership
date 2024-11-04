package com.example.carDealership.domain.carCollecting.entities;

import com.example.carDealership.domain.validations.ValidattionException;
import com.example.carDealership.domain.warehouse.Stock;
import jakarta.persistence.Entity;

import java.util.Date;

// From the Loading form for car dropping at warehouse
@Entity
public class Loading {
    private CarCollection relatedCarCollection;
    private Stock relatedStock;

    private long id;
    private String receivedEmployeeId;
    private Date timestamp;
    private String comment;

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
