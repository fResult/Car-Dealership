package dev.fResult.carDealership.domains;

public interface EventBus {
    // NOTE: We can receive `metadata` as XML, JSON, GRPC, Protobuf, Binary or other
    void publish(DomainEvent event, String metadata);
    void subscribe(EventBusSubscriber subscriber);
}
