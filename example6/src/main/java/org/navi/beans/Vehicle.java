package org.navi.beans;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
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

    @PostConstruct
    public void initialize() {
        this.name = "Mustang";
    }

    @PreDestroy
    public void destroy() {
        System.out.println("@Component: Vehicle is destroying.");
    }

}
