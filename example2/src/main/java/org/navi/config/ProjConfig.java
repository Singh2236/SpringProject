package org.navi.config;

import org.navi.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjConfig {

    @Bean  //Means that whenever we create an object of Vehicle class through spring context we will get this bean and
            // the name of the vehicle is going to be Audi.
    Vehicle vehicle1() {
        Vehicle veh = new Vehicle();
        veh.setName("Audi");
        return veh;
    }
    @Bean
    Vehicle vehicle2() {
        Vehicle veh = new Vehicle();
        veh.setName("Maruti");
        return veh;
    }
    @Bean
    Vehicle vehicle3() {
        Vehicle veh = new Vehicle();
        veh.setName("Kia");
        return veh;
    }

}
