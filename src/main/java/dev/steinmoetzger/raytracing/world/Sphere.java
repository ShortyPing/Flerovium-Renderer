/*
Copyright (c) 2015 - 2022 Michael Steinmötzger
All rights are reserved for this project, unless otherwise
stated in a license file.
*/

package dev.steinmoetzger.raytracing.world;

import java.awt.*;

public class Sphere {

    private Vector centerVec;
    private float radius;
    private Color color;

    public Sphere(Vector centerVec, float radius, Color color) {
        this.centerVec = centerVec;
        this.radius = radius;
        this.color = color;
    }

    public float intersect(Vector origin, Vector direction) {
        Vector p = origin.vectorSub(centerVec);
        float a = direction.vectorDotProduct(direction);
        float b = 2 * (p.vectorDotProduct(direction));
        float c = (p.vectorDotProduct(p)) - (this.radius * this.radius);
        float d = b * b - 4 * a * c;

        if (d < 0)
            return Float.NaN;

        float sqd = (float) Math.sqrt(d);

        float distance = (-b - sqd) / (2.0f * a);

        if (distance > .1f)
            return distance;

        distance = (-b + sqd) / (2.f * a);

        if (distance > .1f)
            return distance;

        return Float.NaN;
    }

    public Vector getCenterVec() {
        return centerVec;
    }

    public void setCenterVec(Vector centerVec) {
        this.centerVec = centerVec;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
