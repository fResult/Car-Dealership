package com.example.carDealership;

import com.example.carDealership.domain.EventBus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarDealershipApplication {
    public static void main(String[] args) {
        var context = SpringApplication.run(CarDealershipApplication.class, args);

        var bus = context.getBean(EventBus.class);
        var warehouseEventHandler = context.getBean(com.example.carDealership.domain.warehouse.EventHandler.class);
        bus.subscribe(warehouseEventHandler);
    }
}
