package com.example.carDealership.domains.carCollecting.entities;

import com.example.carDealership.domains.carCollecting.valueObjects.CarColor;
import com.example.carDealership.domains.carCollecting.valueObjects.Coordinate;
import com.example.carDealership.domains.carCollecting.valueObjects.Status;
import com.example.carDealership.domains.carCollecting.valueObjects.VehicleRegistrationId;
import com.example.carDealership.domains.validations.ValidationException;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class CarCollection {
    @Id
    @GeneratedValue
    private long id;

    private String reference;
    private Date scheduleTime;
    private String contactName;
    private String contactPhoneNumber;
    private String address;
    private String carModel;

    @Enumerated(EnumType.STRING)
    private CarColor color;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Embedded
    private VehicleRegistrationId vehicleRegistrationId;

    @Embedded
    private Coordinate place;

    @OneToMany
    private List<Pickup> pickups;

    public static CarCollection schedule(
            String reference,
            Date scheduleTime,
            String contactName,
            String contactPhoneNumber,
            String address,
            String carModel,
            CarColor color,
            VehicleRegistrationId vehicleRegistrationId,
            Coordinate place
    ) throws ValidationException {
        var carCollection = new CarCollection();

        if (StringUtils.isBlank(reference))
            throw new ValidationException("Reference number is required");
        if (StringUtils.isBlank(contactName))
            throw new ValidationException("Contact Name is required");
        if (StringUtils.isBlank(contactPhoneNumber))
            throw new ValidationException("Contact Phone Number is required");
        if (StringUtils.isBlank(address))
            throw new ValidationException("Address is required");
        if (StringUtils.isBlank(carModel))
            throw new ValidationException("Car Model is required");
        if (color == null)
            throw new ValidationException("Color is required");
        if (scheduleTime == null)
            throw new ValidationException("Schedule Time is required");
        if (vehicleRegistrationId == null)
            throw new ValidationException("Vehicle Registration Id is required");
        if (place == null)
            throw new ValidationException("Place is required");

        var vehicleRegistrationIdValidationResult = vehicleRegistrationId.validate();
        if (!vehicleRegistrationIdValidationResult.getIsSuccess())
            throw new ValidationException(vehicleRegistrationIdValidationResult.getErrorMessage());

        var coordinateValidationResult = place.validate();
        if (!coordinateValidationResult.getIsSuccess())
            throw new ValidationException(coordinateValidationResult.getErrorMessage());

        carCollection.reference = reference;
        carCollection.scheduleTime = scheduleTime;
        carCollection.contactName = contactName;
        carCollection.contactPhoneNumber = contactPhoneNumber;
        carCollection.address = address;
        carCollection.carModel = carModel;
        carCollection.color = color;
        carCollection.vehicleRegistrationId = vehicleRegistrationId;
        carCollection.status = Status.Scheduled;
        carCollection.place = place;

        return carCollection;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(Date scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public CarColor getColor() {
        return color;
    }

    public void setColor(CarColor color) {
        this.color = color;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public VehicleRegistrationId getVehicleRegistrationId() {
        return vehicleRegistrationId;
    }

    public void setVehicleRegistrationId(VehicleRegistrationId vehicleRegistrationId) {
        this.vehicleRegistrationId = vehicleRegistrationId;
    }

    public Coordinate getPlace() {
        return place;
    }

    public void setPlace(Coordinate place) {
        this.place = place;
    }

    public List<Pickup> getPickups() {
        return pickups;
    }

    public void setPickups(List<Pickup> pickups) {
        this.pickups = pickups;
    }

    public void carDroppedToWarehouse() throws ValidationException {
        if (status != Status.Scheduled)
            throw new ValidationException("Car Collection already dropped");

        this.setStatus(Status.Dropped);
    }


    public void recordPickup(PickupResult result, String reason) throws ValidationException {
        var pickup = new Pickup(new Date(), result, reason);
        var pickupValidationResult = pickup.validate();

        if (!pickupValidationResult.getIsSuccess())
            throw new ValidationException(pickupValidationResult.getErrorMessage());

        if (status != Status.Scheduled)
            throw new ValidationException("Car Collection is not scheduled for picking up. Cannot record pickup");

        pickups.add(pickup);
        if (pickup.getResult() == PickupResult.Success) {
            status = Status.PickedUp;
        }
    }

    public List<Pickup> listPickup() {
        throw new UnsupportedOperationException();
    }

    public void changePickupResult(Long pickupId, PickupResult status) {
        throw new UnsupportedOperationException();
    }
}
