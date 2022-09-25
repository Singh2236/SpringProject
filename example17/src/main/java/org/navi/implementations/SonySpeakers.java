package org.navi.implementations;

import org.navi.interfaces.Speakers;
import org.springframework.stereotype.Component;

@Component
public class SonySpeakers implements Speakers {
    private String name = "Sony";

    public String getName() {
        return name;
    }

    @Override
    public String makeSound() {
        return "Sony Speakers are making sound";
    }
}
