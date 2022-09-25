package org.navi.implementations;

import org.navi.interfaces.Speakers;
import org.navi.model.Song;
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
    public String makeSound(Song song) {
        return "Playing " + song.getTitle() + " by " + song.getSingerName();
    }

}
