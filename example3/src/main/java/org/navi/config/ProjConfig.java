package org.navi.config;

import org.navi.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjConfig {

    @Bean(name = "Audi")
    Vehicle vehicle1() {
        Vehicle veh = new Vehicle();
        veh.setName("Audi");
        return veh;
    }
    @Bean(value = "Maruti")
    Vehicle vehicle2() {
        Vehicle veh = new Vehicle();
        veh.setName("Maruti");
        return veh;
    }
    @Bean("kia")
    Vehicle vehicle3() {
        Vehicle veh = new Vehicle();
        veh.setName("Kia");
        return veh;
    }

}
