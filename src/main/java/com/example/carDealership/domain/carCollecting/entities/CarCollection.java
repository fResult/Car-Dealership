package com.example.carDealership.domain.carCollecting.entities;

import com.example.carDealership.domain.carCollecting.valueObjects.CarColor;
import com.example.carDealership.domain.carCollecting.valueObjects.Coordinate;
import com.example.carDealership.domain.carCollecting.valueObjects.Status;
import com.example.carDealership.domain.carCollecting.valueObjects.VehicleRegistrationId;
import com.example.carDealership.domain.validation.ValidationException.ValidattionException;
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
    ) throws ValidattionException {
        var carCollection = new CarCollection();

        if (StringUtils.isBlank(reference))
            throw new ValidattionException("Reference number is required");
        if (StringUtils.isBlank(contactName))
            throw new ValidattionException("Contact Name is required");
        if (StringUtils.isBlank(contactPhoneNumber))
            throw new ValidattionException("Contact Phone Number is required");
        if (StringUtils.isBlank(address))
            throw new ValidattionException("Address is required");
        if (StringUtils.isBlank(carModel))
            throw new ValidattionException("Car Model is required");
        if (color == null)
            throw new ValidattionException("Color is required");
        if (scheduleTime == null)
            throw new ValidattionException("Schedule Time is required");

        var vehicleRegistrationIdValidationResult = vehicleRegistrationId.validate();
        if (!vehicleRegistrationIdValidationResult.getIsSuccess())
            throw new ValidattionException(vehicleRegistrationIdValidationResult.getErrorMessage());

        var coordinateValidationResult = place.validate();
        if (!coordinateValidationResult.getIsSuccess())
            throw new ValidattionException(coordinateValidationResult.getErrorMessage());

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




}
