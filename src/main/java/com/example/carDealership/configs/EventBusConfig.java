package com.example.carDealership.configs;

import com.example.carDealership.domain.DomainEvent;
import com.example.carDealership.domain.EventBus;
import com.example.carDealership.domain.EventBusSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventBusConfig {
    @Bean
    public EventBus eventBus() {
        return new EventBus() {
            @Override
            public void publish(DomainEvent event, String metadata) {
            }

            @Override
            public void subscribe(EventBusSubscriber subscriber) {
            }
        };
    }
}
