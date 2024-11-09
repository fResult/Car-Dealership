package dev.fResult.carDealership.controllers.carCollecting;

import dev.fResult.carDealership.controllers.CarCollectionRequest;
import dev.fResult.carDealership.controllers.CarCollectionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car-collection")
public class ScheduleController {
    @PostMapping("/schedule")
    public ResponseEntity<CarCollectionResponse> schedule(@RequestBody CarCollectionRequest carCollection) {
        // var carCollection = CarCollection.schedule();

        var detail = ProblemDetail.forStatusAndDetail(HttpStatus.METHOD_NOT_ALLOWED, "Not Implement Yet");
        return ResponseEntity.of(detail).build();
    }
}
