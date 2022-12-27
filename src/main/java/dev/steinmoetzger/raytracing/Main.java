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
import java.util.Random;

public class Main extends JFrame {


    private World world;

    public Main() {
        this.setTitle("Ray Tracing");
        this.setSize(1024, 1024);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.world = new World();
        this.world.addSphere(new Sphere(new Vector(1, 1, -1), 1, Color.RED));
        this.world.createLight(1, 1, -1, 100);


        while (this.world.getLights().get(0).getCenterVec().getY() != -6) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.world.getLights().get(0).getCenterVec().addY(.1f);
            this.repaint();
        }

    }


    @Override
    public void paint(Graphics g) {
        for (int y = 0; y < 1024; y++) {
            for (int x = 0; x < 1024; x++) {
                Vector direction = new Vector(512, 512, 1024).unitVector();

                Color c = trace(new Vector(0, 0, 0), direction);

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


        Color c = world.getSpheres().get(idx).getColor();

        for (var light : world.getLights()) {
            Vector l = light.getCenterVec().vectorSub(p).unitVector();
            boolean shadow = false;
            for (var sphere : world.getSpheres()) {
                if (!Float.isNaN(sphere.intersect(p, l))) {
                    shadow = true;
                }
            }


            float d = light.getRange() - light.getCenterVec().vectorSub(p).len();

            int d1 = (int) d;

            if (!shadow || d <= 0 || d1 <= 0) {
                return Color.BLACK;
            }




            c = new Color(c.getRed() / d1, c.getGreen() / d1, c.getBlue() / d1);


        }


        return c;
    }

    private Color calcShader(Color c, Sphere light, float diffuse, float specular) {
        return new Color(((c.getRed() * light.getColor().getRed() * diffuse + specular) % 1),
                ((c.getGreen() * light.getColor().getGreen() * diffuse + specular) % 1),
                ((c.getBlue() * light.getColor().getBlue() * diffuse + specular) % 1)
        );
    }

    public static void main(String[] args) {
        new Main();

        Vector vec = new Vector(5, 9, 7);

        System.out.println(vec.unitVector());
    }
}
