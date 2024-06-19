package org.example;


import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

// Класс BoidRunner, который управляет симуляцией и отображением
public class BoidRunner extends JPanel {
    private static final long serialVersionUID = -8716187417647724411L;

    public static final int BOIDCOUNT = 1200; // Количество Boids в симуляции
    public static final int WIDTH = 1920; // Ширина окна
    public static final int HEIGHT = 1080; // Высота окна
    static ArrayList<Boid> flock = new ArrayList<Boid>();

    public BoidRunner() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);

        for(int i = 0; i < BOIDCOUNT; i++)
            flock.add(new Boid((int)(Math.random() * WIDTH), (int)(Math.random() * HEIGHT)));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for(Boid boid: flock) {
            boid.draw(g2d);
        }
    }

    public void run() {
        while(true) {
            for(Boid boid: flock) {
                boid.edges();
                boid.flock(flock);
                boid.update();
            }
            repaint();
            try {
                Thread.sleep(10);
            } catch( InterruptedException ex ) {
                Thread.currentThread().interrupt();
            }
        }
    }
}