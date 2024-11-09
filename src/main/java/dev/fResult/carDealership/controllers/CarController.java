package dev.fResult.carDealership.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    @GetMapping
    public List<String> getCars() {
        return List.of("Car 1", "Car 2");
    }
}
