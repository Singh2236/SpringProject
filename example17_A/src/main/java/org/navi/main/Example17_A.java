package org.navi.main;

import org.navi.beans.Person;
import org.navi.config.ProjectConfig;
import org.navi.model.Song;
import org.navi.service.VehicleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example17_A {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        Person person = context.getBean(Person.class);
        VehicleService vehicleService = context.getBean(VehicleService.class);

        System.out.println(vehicleService.getClass());   // // For now the data type of this class is just the bean above but in AOP it will be some proxy class

        Song song  = new Song();
        song.setTitle("Black to Black");
        song.setSingerName("Amy");

        Boolean vehicleStarted = true;

        System.out.println(person.getVehicle().getVehicleService().playMusic(vehicleStarted,song));

        System.out.println(person.getVehicle().getVehicleService().moveVehicle(vehicleStarted));

        System.out.println(person.getVehicle().getVehicleService().applyBreaks(vehicleStarted));
    }
}
