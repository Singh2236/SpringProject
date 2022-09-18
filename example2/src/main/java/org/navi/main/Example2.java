package org.navi.main;

import org.navi.beans.Vehicle;
import org.navi.config.ProjConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example2 {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjConfig.class);

        Vehicle vehicle = context.getBean("vehicle1", Vehicle.class);
        System.out.println(vehicle.getName());
    }
}
