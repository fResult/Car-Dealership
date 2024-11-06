package com.example.carDealership.controllers.carCollecting;

import com.example.carDealership.controllers.CarCollectionRequest;
import com.example.carDealership.controllers.CarCollectionResponse;
import org.springframework.http.HttpStatusCode;
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

        var detail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(405), "Not Implement Yet");
        return ResponseEntity.of(detail).build();
    }
}
