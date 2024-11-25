package dev.fResult.carDealership.domains.carCollecting.valueObjects;

import dev.fResult.carDealership.domains.validations.ValidationException;
import dev.fResult.carDealership.domains.validations.ValidationResult;
import jakarta.persistence.Embeddable;

/* NOTE: Just for POCing the record class with validation */
@Embeddable
public record CoordinateData(double latitude, double longitude) {
  public static CoordinateData of(double latitude, double longitude) throws ValidationException {
    ValidationResult validationResult = validate(latitude, longitude);
    if (!validationResult.getIsSuccess()) {
      throw new ValidationException(validationResult.getErrorMessage());
    }

    return new CoordinateData(latitude, longitude);
  }

  private static ValidationResult validate(double latitude, double longitude) {
    if (latitude <= 90 && latitude >= -90 && longitude <= 180 && longitude >= -180) {
      return ValidationResult.success();
    }

    return ValidationResult.fail(
        "Latitude must be in range of -90 and 90. Longitude must be in range of -180 and 180.");
  }
}
