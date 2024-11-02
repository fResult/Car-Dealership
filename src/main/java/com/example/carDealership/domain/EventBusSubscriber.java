package com.example.carDealership.domain;

public interface EventBusSubscriber {
    void handleEvent(DomainEvent event, String metadata);
}
