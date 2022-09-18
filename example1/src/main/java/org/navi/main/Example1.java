package org.navi.main;

import org.navi.beans.Vehicle;
import org.navi.config.ProjConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example1 {
    public static void main(String[] args) {

        Vehicle vehicle = new Vehicle();
        vehicle.setName("Maruti");
        System.out.println("Vehicle name from non-spring context: " + vehicle.getName());


        var context = new AnnotationConfigApplicationContext(ProjConfig.class);

        Vehicle veh = context.getBean(Vehicle.class);
        System.out.println("Vehicle's name from the spring context: " + veh.getName());

        /*
        * We don't need to do any explicit casting while fetching a Bean from the context.
        * Spring is smart enough to look for a Bean of the type you requested in its context.
        * If such a Bean doesn't exist, it will through an error.
        * */

        String stringBean = context.getBean(String.class);
        System.out.println("String from the spring context: "+stringBean);

        Integer integerBean = context.getBean(Integer.class);
        System.out.println("Integer from the Spring context: "+integerBean);
    }
}
