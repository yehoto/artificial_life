package org.example;


import javax.swing.*;
import java.awt.*;
import java.util.*;

public class BoidRunner extends JPanel  {
    private static final long serialVersionUID = -8716187417647724411L;
    public static final int BOIDCOUNT = 10 ;
    public static final int FOODCOUNT = 1000 ;

   public static int WIDTH;
    public static int HEIGHT;

    static {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        WIDTH = ((int)screenSize.getWidth()) - 200;
        HEIGHT = (int)screenSize.getHeight();
    }

    static ArrayList<Boid> flock = new ArrayList<>();
    static ArrayList<Food> goodFood = new ArrayList<>();
    static ArrayList<Food> badFood = new ArrayList<>();


    public BoidRunner() {
        this.setLayout(null);
        this.setBackground(new Color(46, 51, 48));
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setFocusable(true);


        for(int i = 0; i < BOIDCOUNT; i++)
            flock.add(new Boid());

        for(int i = 0; i < FOODCOUNT; i++)
            goodFood.add(new Food(false));

        for(int i = 0; i < BOIDCOUNT; i++)
            badFood.add(new Food(true));

    }

    @Override
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        Graphics2D g = (Graphics2D) page;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for(Boid boid: flock) {
            boid.draw(g);
        }

        for(Food f: goodFood) {
            f.draw(g);
        }
        for(Food f: badFood) {
            f.draw(g);
        }
    }


    public void run() {
        while(true) {

            for(int i = 0; i < flock.size(); i++){
                flock.get(i).edges();
                flock.get(i).behaviors(goodFood,badFood);
                flock.get(i).update();
            }

            this.repaint();
            try {
                Thread.sleep(10);
            } catch( InterruptedException ex ){}
        }
    }

}