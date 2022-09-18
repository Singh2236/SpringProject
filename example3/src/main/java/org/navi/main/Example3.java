package org.navi.main;

import org.navi.beans.Vehicle;
import org.navi.config.ProjConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example3 {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjConfig.class);

        Vehicle veh1 = context.getBean("Audi", Vehicle.class);
        System.out.println(veh1.getName());

        Vehicle veh2 = context.getBean("Maruti", Vehicle.class);
        System.out.println(veh2.getName());

        Vehicle veh3 = context.getBean("kia", Vehicle.class);
        System.out.println(veh3.getName());

    }
}
