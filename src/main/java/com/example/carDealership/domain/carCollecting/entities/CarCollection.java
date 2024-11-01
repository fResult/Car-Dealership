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

}
