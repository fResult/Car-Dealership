package dev.fResult.carDealership.domains;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/* NOTE:
 * We can also use Message Queue Libraries instead of LocalEventBus.
 * E.g. class MessageQueueEventBus implements EventBus {}
 */
@Primary
@Component
public class LocalEventBus implements EventBus {
  List<EventBusSubscriber> subscribers = new ArrayList<>();

  @Override
  public void publish(DomainEvent event, String metadata) {
    subscribers.forEach(subscriber -> subscriber.handleEvent(event, metadata));
  }

  @Override
  public void subscribe(EventBusSubscriber subscriber) {
    subscribers.add(subscriber);
  }
}
