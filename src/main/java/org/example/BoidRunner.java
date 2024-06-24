package org.example;


import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;

public class BoidRunner extends JPanel  {
    private static final long serialVersionUID = -8716187417647724411L;
    public static final int BOIDCOUNT = 2               ;
    public static final int FOODCOUNT = 100 ;
    public static double maxSpeed = 2;
    public static double  max_force = 0.5;
    public static int food_value  = 1;   // health gained from a food
    public static int poison_value = -2;   // health lost from a poison

    public static double mutation_rate = 0.3;

    public static int food_attract = 1;
    public static int poison_attract = 1;
    public static int food_percept = 50;
    public static int poison_percept = 50;

    public static double health_tick = 0.5;

   public static int WIDTH;
    public static int HEIGHT;

    static {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        WIDTH = (((int)screenSize.getWidth()) /100)*80;
        HEIGHT = (int)screenSize.getHeight();
    }

    static ArrayList<Boid> flock = new ArrayList<>();
    static ArrayList<Food> goodFood = new ArrayList<>();
    static ArrayList<Food> badFood = new ArrayList<>();


    public BoidRunner() {
       // this.setLayout(null);
        this.setBackground(new Color(46, 51, 48));
//        this.setPreferredSize(new Dimension(5, HEIGHT));
//        this.setFocusable(true);


        for(int i = 0; i < BOIDCOUNT; i++)
            flock.add(new Boid(false,null,null));

        for(int i = 0; i < FOODCOUNT; i++)
            goodFood.add(new Food(false));

        for(int i = 0; i < BOIDCOUNT; i++)
            badFood.add(new Food(true));

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                healthTick(); // Вызов статического метода healthTick класса BoidRunner
            }
        };

        // Запуск таймера с задержкой 0 миллисекунд и периодом в 1000 миллисекунд (1 секунда)
        timer.schedule(task, 0, 1000);

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

    public static void healthTick(){
        for(Boid b: flock) {
            double value ;
            value=b.health;
            value-=health_tick;

            b.health=round(value,1);

        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}