package org.navi.main;

import org.navi.beans.Person;
import org.navi.config.ProjectConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example12 {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Person person = context.getBean("person",Person.class);
        System.out.println("Person's name: " + person.getName());
        System.out.println("Person's Car: " + person.getVehicle().getName());
    }

}
