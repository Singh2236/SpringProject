package org.navi.service;

import org.navi.interfaces.Speakers;
import org.navi.interfaces.Tyres;
import org.navi.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class VehicleService {
    private Logger logger = Logger.getLogger(VehicleService.class.getName()); //TODO: Ponder on .class and getName()

    @Autowired
    private Speakers speakers;
    @Autowired
    private Tyres tyres;

    public Speakers getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Speakers speakers) {
        this.speakers = speakers;
    }

    public Tyres getTyres() {
        return tyres;
    }

    public void setTyres(Tyres tyres) {
        this.tyres = tyres;
    }



    public String playMusic(Boolean vehicleStarted, Song song) {
        Instant start = Instant.now();
        logger.info("EXECUTION OF METHOD STARTED");
        String music = null;
        if (vehicleStarted) {
            music = speakers.makeSound(song);
        } else {
            logger.log(Level.SEVERE, "Vehicle not started to perform the operation");
        }
        logger.info("Method execution ended.");
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        logger.info("Time took to execute the method " + timeElapsed);
        return music;

    }

    public String moveVehicle(Boolean vehicleStarted) {
        Instant start = Instant.now();
        logger.info("EXECUTION OF METHOD STARTED");
        String status = null;
        if (vehicleStarted) {
            status = tyres.rotate();
        } else {
            logger.log(Level.SEVERE, "Vehicle not started to perform the operation");
        }
        logger.info("Method execution ended.");
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        logger.info("Time took to execute the method " + timeElapsed);
        return status;
    }

    public String applyBreaks(Boolean vehicleStarted) {
        Instant start = Instant.now();
        logger.info("EXECUTION OF METHOD STARTED");
        String status = null;
        if (vehicleStarted) {
            status = tyres.stop();
        } else {
            logger.log(Level.SEVERE, "Vehicle not started to perform the operation");
        }
        logger.info("Method execution ended.");
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        logger.info("Time took to execute the method " + timeElapsed);
        return status;
    }


}
