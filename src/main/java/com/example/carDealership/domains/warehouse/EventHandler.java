package com.example.carDealership.domains.warehouse;

import com.example.carDealership.domains.DomainEvent;
import com.example.carDealership.domains.EventBusSubscriber;
import com.example.carDealership.persistences.StockRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventHandler implements EventBusSubscriber {
    @Autowired
    private StockRepository stockRepository;

    @Override
    public void handleEvent(DomainEvent event, String metadata) {
        if (event != DomainEvent.CarDropped) return;

        try {
            // Turn JSON into CarDroppedEvent`
            var mapper = new ObjectMapper();
            var eventMetadata = mapper.readValue(metadata, CarDroppedEvent.class);
            var carModel = eventMetadata.getModel();

            // Execute domain logic
            var stock = stockRepository.findOneByModel(carModel);
            var stockToSave = stock.orElseGet(() -> new Stock(carModel, 0));
            stockToSave.incrementStockQuantity(1);
            stockRepository.save(stockToSave);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }
}
