package org.navi.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class Person {
    String name = "Lucy";
    private Vehicle vehicle;

    @Autowired
    public Person(Vehicle vehicle1) {
        System.out.println("person bean -->----->----->--->");
        this.vehicle = vehicle1;
    }

    public String getName() {
        return name;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
