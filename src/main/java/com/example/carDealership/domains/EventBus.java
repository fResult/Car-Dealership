package com.example.carDealership.domains;

public interface EventBus {
    void publish(DomainEvent event, String metadata);
    void subscribe(EventBusSubscriber subscriber);
}
