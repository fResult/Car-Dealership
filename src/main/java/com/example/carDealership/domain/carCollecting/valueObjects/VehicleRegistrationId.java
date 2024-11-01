package com.example.carDealership.domain.carCollecting.valueObjects;

import com.example.carDealership.domain.carCollecting.validations.ValidationResult;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
public class VehicleRegistrationId {
    @Column(name = "vehicle_registration_id")
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public VehicleRegistrationId() {

    }

    public VehicleRegistrationId(String id) {
        this.id = id;
    }

    public ValidationResult isValid() {
        String regexPattern = "[\\w\\s]*[-\\s]?[\\w\\s]*";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(id);

        boolean isMatch = matcher.matches();
        if (!isMatch) {
            return ValidationResult.fail("Invalid Vehicle Registration Number");
        }
        return ValidationResult.success();
    }
}
