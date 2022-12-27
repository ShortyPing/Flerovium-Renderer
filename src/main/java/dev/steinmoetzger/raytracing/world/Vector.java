/*
Copyright (c) 2015 - 2022 Michael Steinmötzger
All rights are reserved for this project, unless otherwise
stated in a license file.
*/

package dev.steinmoetzger.raytracing.world;

import dev.steinmoetzger.raytracing.Main;

public class Vector {

    private float x;
    private float y;
    private float z;

    public Vector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }


    public void addX(float i) {
        this.x += i;
    }

    public void addY(float i) {
        this.y += i;
    }

    public void addZ(float i) {
        this.z += i;
    }

    public Vector vectorAdd(Vector vector) {
        return new Vector(this.x + vector.getX(), this.y + vector.getY(), this.z + vector.getZ());
    }

    public Vector vectorSub(Vector vector) {
        return new Vector(this.x - vector.getX(), this.y - vector.getY(), this.z - vector.getZ());
    }


    public Vector vectorMult(float i) {
        return new Vector(this.x * i, this.y * i, this.z * i);
    }

    public float vectorDotProduct(Vector vector) {
        return this.getX() * vector.getX() + this.getY() * vector.getY() + this.getZ() * vector.getZ();
    }

    public float len() {
        return (float) Math.sqrt(vectorDotProduct(this));
    }

    public Vector unitVector() {
        return vectorMult(1 / this.len());
    }

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
