package com.example.carDealership;

import com.example.carDealership.domains.EventBus;
import com.example.carDealership.domains.carCollecting.entities.CarCollection;
import com.example.carDealership.domains.carCollecting.valueObjects.CarColor;
import com.example.carDealership.domains.carCollecting.valueObjects.Coordinate;
import com.example.carDealership.domains.carCollecting.valueObjects.VehicleRegistrationId;
import com.example.carDealership.domains.validations.ValidationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class CarDealershipApplication {
    public static void main(String[] args) {
        var context = SpringApplication.run(CarDealershipApplication.class, args);

        var bus = context.getBean(EventBus.class);
        var warehouseEventHandler = context.getBean(com.example.carDealership.domains.warehouse.EventHandler.class);
        bus.subscribe(warehouseEventHandler);

        testScheduleCarCollection();
    }

    public static void testScheduleCarCollection() {
        var registrationId = new VehicleRegistrationId("SS-SS");
        var pickupPlace = new Coordinate(90, 180);

        try {
            var carCollection = CarCollection.schedule(
                    "Korn",
                    new Date(),
                    "Uncle Kile",
                    "0999999999",
                    "StapleX street",
                    "Civic Type R",
                    CarColor.Black,
                    registrationId,
                    pickupPlace
            );

            System.out.println("Scheduled Car Collection Time: " + carCollection.getScheduleTime());
        } catch (ValidationException ex) {
            ex.printStackTrace();
        }
    }
}
