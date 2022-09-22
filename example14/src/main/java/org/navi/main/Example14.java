package org.navi.main;

import org.navi.beans.Person;
import org.navi.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example14 {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Person person = context.getBean(Person.class);
        System.out.println("Name of the Person " + person.getName());
        System.out.println("Car of the person " + person.getVehicle().getName());
        System.out.println(person.getVehicle().getVehicleService().makeSound());
        System.out.println(person.getVehicle().getVehicleService().moveVehicle());


    }
}
