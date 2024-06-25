package org.example;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoidRunner extends JPanel  {
    private static final long serialVersionUID = -8716187417647724411L;
    public static final int BOIDCOUNT = 1 ;      //+1 из-за клонирования
    public static final int FOODCOUNT = 10;
    public static final int POISONCOUNT = 10 ;
    public static double maxSpeed = 2;
    public static double  max_force = 0.5;
    public static double food_value  = 1;   // health gained from a food
    public static double poison_value = -2;   // health lost from a poison

    public static double mutation_rate = 0.3;

    public static double food_attract = 3;
    public static double poison_attract = 3;
    public static double food_percept = 30;
    public static double poison_percept = 30;

    public static double health_tick = 0.01;

    public static double childInSec  = 5;
    public static double foodInSec  = 5;
    public static double poisonInSec  = 5;
    //private final Lock lock = new ReentrantLock();
    public  static Timer timer1 = new Timer();

    public  static TimerTask task1 = new TimerTask() {
        @Override
        public void run() {
            int i = 0;
            if(! BoidRunner.flock.isEmpty()) {
                do {
                    Random rand = new Random();
                    i = rand.nextInt(BoidRunner.flock.size());
                    // Получаем Boid по случайному индексу и добавляем его потомка в список
                } while (BoidRunner.flock.get(i).dead());
                BoidRunner.flock.add(BoidRunner.flock.get(i).getChild());
            }
        }
    };

    public  static Timer timer2 = new Timer();

    public  static TimerTask task2 = new TimerTask() {
        @Override
        public void run() {
            goodFood.add(new Food(false));
        }
    };

    public  static Timer timer3 = new Timer();

    public  static TimerTask task3 = new TimerTask() {
        @Override
        public void run() {
            badFood.add(new Food(true));
        }
    };

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
        this.setBackground(new Color(46, 51, 48));


        for(int i = 0; i < BOIDCOUNT; i++)
            flock.add(new Boid(false,null,null,null));

        for(int i = 0; i < FOODCOUNT; i++)
            goodFood.add(new Food(false));

        for(int i = 0; i < POISONCOUNT; i++)
            badFood.add(new Food(true));

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {


                // Во время итерации
                for (Boid bird : flock) {
                    double value ;
                     value=bird.health;
                     value-=health_tick;

                      bird.health=round(value,2);
                    bird.age -= 1;
                   // if (bird.health < 0 || bird.age < 0) {
                        if (value < 0.5) {
                        bird.dead = true; // Помечаем для удаления
                    }
                }

// Удаление помеченных элементов
                flock.removeIf(bird -> bird.dead);


            }
        };

        // Запуск таймера с задержкой 0 миллисекунд и периодом в 1000 миллисекунд (1 секунда)
        timer.schedule(task, 0, 1000);



        // Запуск таймера с задержкой 0 миллисекунд и периодом в 1000 миллисекунд (1 секунда)
        timer1.schedule(task1, 0, (int)(childInSec*1000));

        timer2.schedule(task2, 0, (int)(foodInSec*1000));

        timer3.schedule(task3, 0, (int)(poisonInSec*1000));


        // Добавляем обработчик событий мыши
        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    createFoodAt(e.getX(), e.getY(), false);
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    createFoodAt(e.getX(), e.getY(), true);
                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    createBoidAt(e.getX(), e.getY());
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    createFoodAt(e.getX(), e.getY(), false);
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    createFoodAt(e.getX(), e.getY(), true);
                }
            }

            private void createFoodAt(int x, int y, boolean isPoison) {
                SwingUtilities.invokeLater(() -> {
                    if (isPoison) {
                        badFood.add(new Food(true, x, y));
                    } else {
                        goodFood.add(new Food(false, x, y));
                    }
                    repaint();
                });
            }

            private void createBoidAt(int x, int y) {
                SwingUtilities.invokeLater(() -> {
                    flock.add(new Boid(x, y));
                    repaint();
                });
            }
        };

        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);

        // Добавляем обработчик клавиатурных событий

        // Установка глобальных горячих клавиш
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED && (e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_F:
                            removeAllFood();
                            break;
                        case KeyEvent.VK_P:
                            removeAllPoison();
                            break;
                        case KeyEvent.VK_O:
                            removeAllBoids();
                            break;
                    }
                }
                return false; // Позволяет дальнейшую обработку события
            }

            // ... методы removeAllFood, removeAllPoison, removeAllBoids ...
            private void removeAllFood() {
                SwingUtilities.invokeLater(() -> {
                    goodFood.clear();
                    repaint();
                });
            }

            private void removeAllPoison() {
                SwingUtilities.invokeLater(() -> {
                    badFood.clear();
                    repaint();
                });
            }

            private void removeAllBoids() {
                SwingUtilities.invokeLater(() -> {
                    flock.clear();
                    repaint();
                });
            }
        });



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



    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}