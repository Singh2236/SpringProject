package org.navi.main;

import org.navi.beans.Person;
import org.navi.beans.Vehicle;
import org.navi.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example9 {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        Vehicle vehicle = context.getBean(Vehicle.class);
        System.out.println("Vehicle from the Spring context: " + vehicle.getName());

        Person person = context.getBean(Person.class);
        System.out.println("Person from the Spring context: " + person.getName());
        System.out.println("Vehicle of the Person form the Spring context: " + person.getVehicle().getName());

    }
}
