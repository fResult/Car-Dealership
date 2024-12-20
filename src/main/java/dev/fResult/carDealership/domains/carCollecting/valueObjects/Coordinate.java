package dev.fResult.carDealership.domains.carCollecting.valueObjects;

import dev.fResult.carDealership.domains.validations.ValidationResult;
import jakarta.persistence.Embeddable;

/* NOTE:
 * Even though this class is a value object
 * But we need to have attribute setter, because it works with the JPA
 */
@Embeddable
public class Coordinate {
  private double latitude;
  private double longitude;

  public Coordinate() {}

  public Coordinate(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public double getLatitude() {
    return latitude;
  }

  public ValidationResult validate() {
    if (latitude <= 90 && latitude >= -90 && longitude <= 180 && longitude >= -180) {
      return ValidationResult.success();
    }

    return ValidationResult.fail(
        "Latitude must be in range of -90 and 90. Longitude must be in range of -180 and 180.");
  }
}
