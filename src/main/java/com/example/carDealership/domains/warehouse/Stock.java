package com.example.carDealership.domains.warehouse;

import jakarta.persistence.*;

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
