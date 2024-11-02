package com.example.carDealership.domain.carCollecting.entities;

import com.example.carDealership.domain.carCollecting.validations.ValidationResult;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Pickup {
    @Id
    @GeneratedValue
    private long id;

    private Date time;
    private PickupResult result;
    private String reason;

    protected Pickup() {
    }

    protected Pickup(Date time, PickupResult result, String reason) {
        this.time = time;
        this.result = result;
        this.reason = reason;
    }

    protected ValidationResult validate() {
        if (result == PickupResult.Rescheduled && StringUtils.isBlank(reason)) {
            return ValidationResult.fail("Please provide reason for rescheduling");
        }

        return ValidationResult.success();
    }
}
