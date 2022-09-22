package org.navi.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("person") //personBean1 of Person with dependency on VehicleBean1
@Scope()
public class Person {
    String name = "Lucy";

    /*@Autowired*/
    private Vehicle vehicle;

    @Autowired // Autowired at constructor // Person Bean has dependency on Vehicle
    public Person(Vehicle vehicle1) {
        //System.out.println("Person created by Spring");
        this.vehicle = vehicle1;
    }

    public String getName() {
        return name;
    }


    /*public void setName(String name) {
        this.name = name;
    }*/

    public Vehicle getVehicle() {
        return vehicle;
    }
    /*@Autowired*/
    /*public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }*/
}
