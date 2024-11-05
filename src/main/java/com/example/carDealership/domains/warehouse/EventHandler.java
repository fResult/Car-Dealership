package com.example.carDealership.domains.warehouse;

import com.example.carDealership.domains.DomainEvent;
import com.example.carDealership.domains.EventBusSubscriber;
import com.example.carDealership.persistences.StockRepository.StockRepository;
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
        if (event == DomainEvent.CarDropped) {
            try {
                // Turn JSON into CarDroppedEvent`
                var mapper = new ObjectMapper();
                var eventMetadata = mapper.readValue(metadata, CarDroppedEvent.class);

                // Execute domain logic
                var stock = stockRepository.findByModel(eventMetadata.getModel()).getFirst();
                stockRepository.save(stock);
            } catch (JsonProcessingException ex) {
                ex.printStackTrace();
            }
        }
    }
}
