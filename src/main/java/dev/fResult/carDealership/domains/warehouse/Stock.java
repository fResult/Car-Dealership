package dev.fResult.carDealership.domains.warehouse;

import jakarta.persistence.*;

@Entity
public class Stock {
  @Id @GeneratedValue private long id;
  private String model;
  private long quantity;

  public Stock() {}

  public Stock(String model, long quantity) {
    this.model = model;
    this.quantity = quantity;
  }

  public void incrementStockQuantity(int count) {
    quantity += count;
  }
}
