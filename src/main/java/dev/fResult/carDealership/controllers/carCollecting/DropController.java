package dev.fResult.carDealership.controllers.carCollecting;

import dev.fResult.carDealership.controllers.CarCollectionResponse;
import dev.fResult.carDealership.domains.carCollecting.CarCollectingUseCases;
import dev.fResult.carDealership.domains.validations.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/car-collection")
public class DropController {
    CarCollectingUseCases carCollectingUseCases;

    public DropController(CarCollectingUseCases carCollectingUseCases) {
        this.carCollectingUseCases = carCollectingUseCases;
    }

    @PostMapping("/{id}/drop")
    public ResponseEntity<CarCollectionResponse> dropCar(@PathVariable int id) {
        try {
            var carCollection = carCollectingUseCases.dropCarAtWarehouse(id);
            return ResponseEntity.created(URI.create("/car-collection/" + id + "/drop"))
                    .body(CarCollectionResponse.fromEntity(carCollection));
        } catch (ValidationException ex) {
            var detail = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
            return ResponseEntity.of(detail).build();
        }
    }
}
