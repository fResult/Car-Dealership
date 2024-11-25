package dev.fResult.carDealership.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {
  @GetMapping
  public List<String> getCars() {
    return List.of("Car 1", "Car 2");
  }
}
