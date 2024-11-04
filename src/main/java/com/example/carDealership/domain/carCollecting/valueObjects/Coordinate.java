package com.example.carDealership.domain.carCollecting.valueObjects;

import com.example.carDealership.domain.validations.ValidationResult;
import jakarta.persistence.Embeddable;

@Embeddable
public class Coordinate {
    private final double latitude;
    private final double longitude;

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public ValidationResult validate() {
        if (latitude <= 90 && latitude >= 90 && longitude <= 180 && longitude >= -180) {
            return ValidationResult.success();
        }

        return ValidationResult.fail(
                "Latitude must be in range of -90 and 90. Longitude must be in range of -180 and 180."
        );
    }
}
