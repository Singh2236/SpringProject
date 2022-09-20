package org.navi.config;

import org.navi.beans.Person;
import org.navi.beans.Vehicle;
import org.springframework.context.annotation.Bean;

public class ProjectConfig {
    @Bean
    public Vehicle vehicle(){
        Vehicle vehicle = new Vehicle();
        vehicle.setName("Toyota");
        return vehicle;
    }

    @Bean
    public Person person(){
        Person person = new Person();
        person.setName("Lucy");
        person.setVehicle(vehicle());
        return person;
    }

}
