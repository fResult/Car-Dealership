package com.example.carDealership.domains.carCollecting;

import com.example.carDealership.domains.CarCollectingRepository;
import com.example.carDealership.domains.DomainEvent;
import com.example.carDealership.domains.EventBus;
import com.example.carDealership.domains.carCollecting.entities.CarCollection;
import com.example.carDealership.domains.validations.ValidattionException;
import com.example.carDealership.domains.warehouse.CarDroppedEvent;
import com.example.carDealership.persistences.StockRepository.StockRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarCollectingUseCases {
    private CarCollectingRepository carCollectingRepository;

    @Autowired
    private EventBus eventBus;

    public CarCollectingUseCases(CarCollectingRepository carCollectingRepository) {
        this.carCollectingRepository = carCollectingRepository;
    }

    public CarCollection dropCarAtWarehouse(long carCollectionId) throws ValidattionException {
        var carCollection = carCollectingRepository.findCarCollectionById(carCollectionId).orElseThrow();

        carCollection.carDroppedToWarehouse();
        carCollection = carCollectingRepository.saveCarCollection(carCollection);

        var carDroppedEventMetadata = new CarDroppedEvent();
        try {
            var objectMapper = new ObjectMapper();
            eventBus.publish(DomainEvent.CarDropped, objectMapper.writeValueAsString(carDroppedEventMetadata));
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }

        return carCollection;
    }
}
