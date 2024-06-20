package org.example;


import javax.swing.*;
import java.awt.*;
import java.util.*;

public class BoidRunner extends JPanel  {
    private static final long serialVersionUID = -8716187417647724411L;
    public static final int BOIDCOUNT = 1045 ;

   public static int WIDTH;
    public static int HEIGHT;

    static {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        WIDTH = screenSize.width - 200;
        HEIGHT = screenSize.height;
    }

    static ArrayList<Boid> flock = new ArrayList<Boid>();


    public BoidRunner() {
        this.setLayout(null);
        this.setBackground(new Color(46, 51, 48));
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setFocusable(true);


        for(int i = 0; i < BOIDCOUNT; i++)
            flock.add(new Boid());

    }

    @Override
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        Graphics2D g = (Graphics2D) page;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for(Boid boid: flock) {
            boid.draw(g);
        }
    }


    public void run() {
        while(true) {

            for(int i = 0; i < flock.size(); i++){
                flock.get(i).edges();
                flock.get(i).update();
            }

            this.repaint();
            try {
                Thread.sleep(10);
            } catch( InterruptedException ex ){}
        }
    }

}