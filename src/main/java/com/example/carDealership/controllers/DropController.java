package com.example.carDealership.controllers;

import com.example.carDealership.domains.carCollecting.CarCollectingUseCases;
import com.example.carDealership.domains.validations.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DropController {
    CarCollectingUseCases carCollectingUseCases;

    public DropController(CarCollectingUseCases carCollectingUseCases) {
        this.carCollectingUseCases = carCollectingUseCases;
    }

    @PostMapping("/car-collection/{id}/drop")
    public CarCollectionResponse dropCar(@PathVariable int id) throws ValidationException {
        var carCollection = carCollectingUseCases.dropCarAtWarehouse(id);

        return CarCollectionResponse.fromEntity(carCollection);
    }
}
