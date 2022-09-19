package org.navi.main;

import org.navi.beans.Vehicle;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Example8 {
    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("beans.xml");
        Vehicle vehicle = context.getBean(Vehicle.class);
        System.out.println(vehicle.getName());

    }
}
