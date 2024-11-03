package com.example.carDealership.domain.carCollecting.entities;

import com.example.carDealership.domain.carCollecting.valueObjects.CarColor;
import com.example.carDealership.domain.carCollecting.valueObjects.Coordinate;
import com.example.carDealership.domain.carCollecting.valueObjects.Status;
import com.example.carDealership.domain.carCollecting.valueObjects.VehicleRegistrationId;
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
