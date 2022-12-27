/*
Copyright (c) 2015 - 2022 Michael Steinmötzger
All rights are reserved for this project, unless otherwise
stated in a license file.
*/

package dev.steinmoetzger.raytracing.world;

import java.awt.*;
import java.util.ArrayList;

public class World {

    private ArrayList<Sphere> spheres;
    private ArrayList<Sphere> lights;

    public World() {
        this.spheres = new ArrayList<>();
        this.lights = new ArrayList<>();
    }

    public void createLight(float x, float y, float z) {
        this.lights.add(new Sphere(new Vector(x, y, z), 0, Color.WHITE));
    }

    public void addSphere(Sphere sphere) {
        this.spheres.add(sphere);
    }

    public ArrayList<Sphere> getSpheres() {
        return spheres;
    }

    public void setSpheres(ArrayList<Sphere> spheres) {
        this.spheres = spheres;
    }

    public ArrayList<Sphere> getLights() {
        return lights;
    }

    public void setLights(ArrayList<Sphere> lights) {
        this.lights = lights;
    }
}
