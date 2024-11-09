package dev.fResult.carDealership;

import dev.fResult.carDealership.domains.EventBus;
import dev.fResult.carDealership.domains.carCollecting.entities.CarCollection;
import dev.fResult.carDealership.domains.carCollecting.valueObjects.CarColor;
import dev.fResult.carDealership.domains.carCollecting.valueObjects.Coordinate;
import dev.fResult.carDealership.domains.carCollecting.valueObjects.VehicleRegistrationId;
import dev.fResult.carDealership.domains.validations.ValidationException;
import dev.fResult.carDealership.domains.warehouse.EventHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class CarDealershipApplication {
    public static void main(String... args) {
        var context = SpringApplication.run(CarDealershipApplication.class, args);

        var bus = context.getBean(EventBus.class);
        var warehouseEventHandler = context.getBean(EventHandler.class);
        bus.subscribe(warehouseEventHandler);

        // TODO: Remove it when finish creating schedule car collection API
        try {
            testScheduleCarCollection();
        } catch (ValidationException ex) {
            ex.printStackTrace();
        }
    }

    private static void testScheduleCarCollection() throws ValidationException {
        var registrationId = new VehicleRegistrationId("SS-SS");
        var pickupPlace = new Coordinate(90, 180);
        // var pickupPlace = CoordinateData.of(90, 180);

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
