package org.navi.implementations;

import org.navi.interfaces.Tyres;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BridgeStoneTyres implements Tyres {

    private String name = "BridgeStoneTyres";

    public String getName() {
        return name;
    }

    @Override
    public String rotate() {
        return "BridgeStoneTyres are rotating";
    }

}
