package com.example.carDealership.domains;

public interface EventBusSubscriber {
    void handleEvent(DomainEvent event, String metadata);
}
