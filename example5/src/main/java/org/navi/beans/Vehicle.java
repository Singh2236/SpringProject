package org.navi.beans;

import org.springframework.stereotype.Component;

@Component
public class Vehicle {
    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String run(){
        return "Vehicle form Component Vehicle is running";
    }
}
