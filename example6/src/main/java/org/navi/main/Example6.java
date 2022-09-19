package org.navi.main;

import org.navi.beans.Vehicle;
import org.navi.config.ProjConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example6 {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjConfig.class);

        Vehicle vehicle = context.getBean(Vehicle.class);
        System.out.println(vehicle.getName()); // Name of a field is initialized using  @PostConstruct Annotation
        vehicle.destroy(); //before destroying the bean, method using @PreDestroy Annotation
    }
}
