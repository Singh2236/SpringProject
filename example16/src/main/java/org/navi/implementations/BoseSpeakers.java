package org.navi.implementations;

import org.navi.interfaces.Speakers;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BoseSpeakers implements Speakers {

    private String name = "Bose";

    public String getName() {
        return name;
    }


    @Override
    public String makeSound() {
        return "Bose Speakers are making sound";
    }
}
