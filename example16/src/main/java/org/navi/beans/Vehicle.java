package org.navi.beans;

import org.navi.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component //vehicleBean1
public class Vehicle {
    private String name = "Audi";
    private final VehicleService vehicleService;

    @Autowired
    public Vehicle(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public String getName() {
        return name;
    }

    public VehicleService getVehicleService() {
        return vehicleService;
    }
}
