package org.navi.beans;

import org.springframework.stereotype.Component;

@Component
public class Vehicle {
    private String name = "Audi";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String printHello() {
        return "@Component: Vehicle is running";
    }

}
