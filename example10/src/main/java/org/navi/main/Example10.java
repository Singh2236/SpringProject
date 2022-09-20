package org.navi.main;

import org.navi.beans.Person;
import org.navi.beans.Vehicle;
import org.navi.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example10 {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        Vehicle vehicle = context.getBean(Vehicle.class);
        System.out.println("Vehicle name from the Spring context: " + vehicle.getName());

        Person person = context.getBean(Person.class);
        System.out.println("Person name form the Spring Context");
        System.out.println("Vehicle name form the Person's Dependency from the Spring Context: "+ person.getVehicle().getName());
    }
}
