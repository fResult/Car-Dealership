package dev.fResult.carDealership.domains.carCollecting;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.fResult.carDealership.domains.CarCollectingRepository;
import dev.fResult.carDealership.domains.DomainEvent;
import dev.fResult.carDealership.domains.EventBus;
import dev.fResult.carDealership.domains.carCollecting.entities.CarCollection;
import dev.fResult.carDealership.domains.validations.ValidationException;
import dev.fResult.carDealership.domains.warehouse.CarDroppedEvent;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// NOTE: For coordinating with between the Repository and Domain Model
@Component
public class CarCollectingUseCases {
  private final CarCollectingRepository carCollectingRepository;

  @Autowired private EventBus eventBus;

  public CarCollectingUseCases(CarCollectingRepository carCollectingRepository) {
    this.carCollectingRepository = carCollectingRepository;
  }

  @Transactional
  public CarCollection dropCarAtWarehouse(long carCollectionId) throws ValidationException {
    var carCollection =
        carCollectingRepository.findCarCollectionById(carCollectionId).orElseThrow();

    carCollection.carDroppedToWarehouse();

    carCollection = carCollectingRepository.saveCarCollection(carCollection);

    // Publish event in JSON before return
    var objectMapper = new ObjectMapper();
    var carDroppedEventMetadata = new CarDroppedEvent();
    carDroppedEventMetadata.setModel(carCollection.getCarModel());

    try {
      eventBus.publish(
          DomainEvent.CarDropped, objectMapper.writeValueAsString(carDroppedEventMetadata));
    } catch (JsonProcessingException ex) {
      ex.printStackTrace();
    }

    return carCollection;
  }
}
