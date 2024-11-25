package dev.fResult.carDealership.domains;

public interface EventBusSubscriber {
  void handleEvent(DomainEvent event, String metadata);
}
