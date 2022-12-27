/*
Copyright (c) 2015 - 2022 Michael Steinmötzger
All rights are reserved for this project, unless otherwise
stated in a license file.
*/

package dev.steinmoetzger.raytracing;

import dev.steinmoetzger.raytracing.world.Sphere;
import dev.steinmoetzger.raytracing.world.Vector;
import dev.steinmoetzger.raytracing.world.World;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {


    private World world;

    public Main() {
        this.setTitle("Ray Tracing");
        this.setSize(1024, 1024);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.world = new World();

    }


    @Override
    public void paint(Graphics g) {
        for (int y = 0; y < 1024; y++) {
            for (int x = 0; x < 1024; x++) {
                Vector direction = new Vector(x - 1024 / 2, 1024 / 2 - y, -1024).unitVector();

                Color c = trace(new Vector(0, 1, 5), direction);

                g.setColor(c);
                g.fillRect(x, y, 1, 1);

            }
        }
    }

    private Color trace(Vector origin, Vector direction) {
        int idx = -1;
        float distance = Float.NaN;

        for (int i = 0; i < world.getSpheres().size(); ++i) {
            float d = world.getSpheres().get(i).intersect(origin, direction);
            if (!(Float.isNaN(d)) && (idx < 0 || d < distance)) {
                distance = d;
                idx = i;
            }
        }
        if (idx < 0) {
            return new Color(0, 0, 0);
        }

        Vector p = origin.vectorAdd(direction).vectorMult(distance);
        Vector n = (p.vectorSub(world.getSpheres().get(idx).getCenterVec())).unitVector();
        Color c = new Color(0, 0, 0);


        for (var light : world.getLights()) {
            Vector l = light.getCenterVec().vectorSub(p).unitVector();
            boolean shadow = false;
            for (var sphere : world.getSpheres()) {
                if (!Float.isNaN(sphere.intersect(p, l))) {
                    shadow = true;
                }
            }

            float diffuse = Float.max(0.f, l.vectorDotProduct(n) * 0.7f);
            float specular = (float) (Math.pow(Math.max(0.f, l.vectorDotProduct(n)), 70.f) * 0.4f);

            c = calcShader(c, light, diffuse, specular);


            if (!shadow) {
                return world.getSpheres().get(idx).getColor();
            }



        }


        return c;
    }

    private Color calcShader(Color c, Sphere light, float diffuse, float specular) {
        return new Color(c.getRed() * light.getColor().getRed() * diffuse + specular,
                c.getGreen() * light.getColor().getGreen() * diffuse + specular,
                c.getBlue() * light.getColor().getBlue() * diffuse + specular);
    }

    public static void main(String[] args) {
        new Main();

        Vector vec = new Vector(5, 9, 7);

        System.out.println(vec.unitVector());
    }
}
