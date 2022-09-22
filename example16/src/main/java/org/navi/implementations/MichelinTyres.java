package org.navi.implementations;

import org.navi.interfaces.Tyres;
import org.springframework.stereotype.Component;

@Component
public class MichelinTyres implements Tyres {

    private String name = "MichelinTyres";

    public String getName() {
        return name;
    }

    @Override
    public String rotate() {
        return "MichelinTyres are rotating";
    }

}
