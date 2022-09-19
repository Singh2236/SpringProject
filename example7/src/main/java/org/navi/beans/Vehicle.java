package org.navi.beans;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Vehicle {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String printHello() {
        return "@Component: Vehicle is running";
    }


    public void initialize() {
        this.name = "Mustang";
    }

    public void destroy() {
        System.out.println("@Component: Vehicle is destroying.");
    }

}
