package org.navi.service;

import org.navi.interfaces.Speakers;
import org.navi.interfaces.Tyres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class VehicleService {
    @Autowired
    private Speakers speakers;
    @Autowired
    private Tyres tyres;

    public String makeSound(){
        String music = speakers.makeSound();
        return music;

    }

    public String moveVehicle() {
        String status = tyres.rotate();
        return status;

    }


}
