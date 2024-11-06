package com.example.carDealership.domains.carCollecting;

import com.example.carDealership.domains.DomainEvent;
import com.example.carDealership.domains.EventBus;
import com.example.carDealership.domains.carCollecting.entities.CarCollection;
import com.example.carDealership.domains.validations.ValidationException;
import com.example.carDealership.domains.warehouse.CarDroppedEvent;
import com.example.carDealership.persistences.CarCollectingRepositoryImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// For coordinating with between the Repository and Domain Model
@Component
public class CarCollectingUseCases {
    private final CarCollectingRepositoryImpl carCollectingRepository;

    @Autowired
    private EventBus eventBus;

    public CarCollectingUseCases(CarCollectingRepositoryImpl carCollectingRepository) {
        this.carCollectingRepository = carCollectingRepository;
    }

    @Transactional
    public CarCollection dropCarAtWarehouse(long carCollectionId) throws ValidationException {
        var carCollection = carCollectingRepository.findCarCollectionById(carCollectionId).orElseThrow();
        var stock = carCollectingRepository.findStockByModel(carModel);

        carCollection.carDroppedToWarehouse();
        stock.orElseThrow().incrementStockQuantity(1);

        carCollection = carCollectingRepository.saveCarCollection(carCollection);
        carCollectingRepository.saveStock(stock.get());

        var carDroppedEventMetadata = new CarDroppedEvent();
        carDroppedEventMetadata.setModel(carCollection.getCarModel());

        try {
            var objectMapper = new ObjectMapper();
            eventBus.publish(DomainEvent.CarDropped, objectMapper.writeValueAsString(carDroppedEventMetadata));
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }

        return carCollection;
    }
}
