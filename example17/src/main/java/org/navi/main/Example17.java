package org.navi.main;

import org.navi.beans.Person;
import org.navi.config.ProjectConfig;
import org.navi.model.Song;
import org.navi.service.VehicleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example17 {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        Person person = context.getBean(Person.class);
        VehicleService vehicleService = context.getBean(VehicleService.class);

        System.out.println(vehicleService.getClass());   //Todo: Ponder on getClass() method.

        Song song  = new Song();
        song.setTitle("Black to Black");
        song.setSingerName("Amy");

        Boolean vehicleStarted = true;

        System.out.println(person.getVehicle().getVehicleService().playMusic(vehicleStarted,song));

        System.out.println(person.getVehicle().getVehicleService().moveVehicle(vehicleStarted));

        System.out.println(person.getVehicle().getVehicleService().applyBreaks(vehicleStarted));
    }
}
