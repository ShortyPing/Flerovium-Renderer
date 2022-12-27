/*
Copyright (c) 2015 - 2022 Michael Steinmötzger
All rights are reserved for this project, unless otherwise
stated in a license file.
*/

package dev.steinmoetzger.raytracing.world;

import java.awt.*;

public class Light extends Sphere {

    private int range;

    public Light(Vector centerVec, float radius, Color color, int range) {
        super(centerVec, radius, color);
        this.range = range;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
