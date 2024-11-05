package com.example.carDealership.controllers;

import com.example.carDealership.domains.carCollecting.entities.CarCollection;
import com.example.carDealership.domains.carCollecting.entities.Pickup;
import com.example.carDealership.domains.carCollecting.valueObjects.CarColor;
import com.example.carDealership.domains.carCollecting.valueObjects.Coordinate;
import com.example.carDealership.domains.carCollecting.valueObjects.Status;

import java.util.Date;
import java.util.List;

public class CarCollectionResponse {
    private long id;
    private String reference;
    private Date scheduleTime;
    private String contactName;
    private String contactPhoneNumber;
    private String address;
    private String carModel;
    private CarColor color;
    private Status status;
    private String vehicleRegistrationId;
    private Coordinate place;
    private List<Pickup> pickups;

    public static CarCollectionResponse fromEntity(CarCollection entity) {
        var response = new CarCollectionResponse();

        response.id = entity.getId();
        response.reference = entity.getReference();
        response.scheduleTime = entity.getScheduleTime();
        response.contactName = entity.getContactName();
        response.contactPhoneNumber = entity.getContactPhoneNumber();
        response.address = entity.getAddress();
        response.carModel = entity.getCarModel();
        response.color = entity.getColor();
        response.status = entity.getStatus();
        response.vehicleRegistrationId = entity.getVehicleRegistrationId().getId();
        response.place = entity.getPlace();

        return response;
    }

    public long getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public Date getScheduleTime() {
        return scheduleTime;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCarModel() {
        return carModel;
    }

    public CarColor getColor() {
        return color;
    }

    public Status getStatus() {
        return status;
    }

    public String getVehicleRegistrationId() {
        return vehicleRegistrationId;
    }

    public Coordinate getPlace() {
        return place;
    }

    public List<Pickup> getPickups() {
        return pickups;
    }
}
