package com.example.carDealership.domain;

public interface EventBus {
    void publish(DomainEvent event, String metadata);
    void subscribe(EventBusSubscriber subscriber);
}
