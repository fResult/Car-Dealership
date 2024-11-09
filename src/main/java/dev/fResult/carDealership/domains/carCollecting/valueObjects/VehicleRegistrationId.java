package dev.fResult.carDealership.domains.carCollecting.valueObjects;

import dev.fResult.carDealership.domains.validations.ValidationResult;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* NOTE:
 * Even though this class is a value object
 * But we need to have attribute setter, because it works with the JPA
 */
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

    public ValidationResult validate() {
        String regexPattern = "[\\w\\s]*[-\\s]?[\\w\\s]*";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(id);

        boolean isMatch = matcher.matches();
        if (!isMatch)
            return ValidationResult.fail("Invalid Vehicle Registration Number");

        return ValidationResult.success();
    }
}
