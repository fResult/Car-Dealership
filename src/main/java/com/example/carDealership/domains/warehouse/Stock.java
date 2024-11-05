package com.example.carDealership.domains.warehouse;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Stock {
    @Id
    @GeneratedValue
    private long id;
    private long quantity;
    private String model;

    public void incrementStockQuantity(int count) {
        quantity += count;
    }
}
