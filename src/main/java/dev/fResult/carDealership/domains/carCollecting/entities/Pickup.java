package dev.fResult.carDealership.domains.carCollecting.entities;

import dev.fResult.carDealership.domains.validations.ValidationResult;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class Pickup {
  @Id @GeneratedValue private long id;

  private Date time;
  private PickupResult result;
  private String reason;

  protected Pickup() {}

  protected Pickup(Date time, PickupResult result, String reason) {
    this.time = time;
    this.result = result;
    this.reason = reason;
  }

  public long getId() {
    return id;
  }

  protected void setId(long id) {
    this.id = id;
  }

  public Date getTime() {
    return time;
  }

  protected void setTime(Date time) {
    this.time = time;
  }

  public PickupResult getResult() {
    return result;
  }

  protected void setResult(PickupResult result) {
    this.result = result;
  }

  public String getReason() {
    return reason;
  }

  protected void setReason(String reason) {
    this.reason = reason;
  }

  protected ValidationResult validate() {
    if (result == PickupResult.Rescheduled && StringUtils.isBlank(reason))
      return ValidationResult.fail("Please provide reason for rescheduling");

    return ValidationResult.success();
  }
}
