package org.example;


import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class Boid {
    Vector position;
    Vector velocity;
    Vector acceleration;
   int health ;
    int age;
    int[] dna = new int[4];

    static int size = 3;
    static Path2D shape = new Path2D.Double();
    static {
        shape.moveTo(0,-size*2);
        shape.lineTo(-size, size*2);
        shape.lineTo(size,size*2);
        shape.closePath();
    }


    public Boid() {
        this.position = new Vector((Math.random()*BoidRunner.WIDTH),(Math.random()*BoidRunner.HEIGHT));
        double angle = Math.random()*360;
        double radius = Math.random()*2+2; //2-4 скорость
        this.velocity = new Vector((radius * Math.cos(angle)), (radius * Math.sin(angle)));
        this.acceleration = new Vector(0,0);
        this.health = 2;
        this.age = (int)(Math.random() * 1000);
        //this.dna = new ArrayList<>((int)(Math.random() * 4 - 2),(int)(Math.random() * 4 - 2));//от -2 до 2 от 0 до 100
        dna[0] = (int)(Math.random() * 4 - 2);// goodfood weight от -2 до 2
        dna[1] = (int)(Math.random() * 4 - 2);// плохая еда яд от -2 до 2
        dna[2] = (int)(Math.random() * 100);// 0 bis 100 восприятие хорошей еды
        dna[3] = (int)(Math.random() * 100);// восприятие плохой еды

    }

    void behaviors(ArrayList<Food> good,ArrayList<Food> bad){
        Vector attraction = this.eat(good,food_value,1000);
        Vector repulsion = this.eat(bad, poison_value, this.dna[3]);

        // Scale the steering forces via. DNA
        attraction.multiply(this.dna[0]);
        repulsion.multiply(this.dna[1]);

      //  this.acceleration.add(attraction);
       // this.acceleration.add(repulsion);

        if ((attraction.getMagnitude()+repulsion.getMagnitude()) != 0) {
            this.acceleration.add(attraction);
            this.acceleration.add(repulsion);
        }else{
            this.wander(); // Вызываем метод wander, если еда не найдена
        }


    }


    Vector eat(ArrayList<Food> targets,int nutrition, double perception){
        double record = Double.MAX_VALUE;
        int closest=-1;

        // цикл по всем таргетам
        for (int i=0;i<targets.size();i++){
            double d = this.position.dist(targets.get(i).position);
            //момент съедания
            if(d<maxSpeed){
                //уничтожение еды
                targets.remove(i);
                this.health+=nutrition;

            }

            //поиск ближайшей еды которая может быть воспринята
            else if (d < record && d < perception) {
                record = d;
                closest = i;
            }

        }

        if (closest != -1) {
            // Steer towards that target
            return this.seek(targets.get(closest).position);
        }

        // Target not found, etc.
        return new Vector(0, 0);





    }



    Vector seek(Vector target){

        Vector desired = new Vector(target.sub(this.position)[0],target.sub(this.position)[1]);

        desired.setMagnitude(maxSpeed);
        // Steering = Desired minus velocity
        Vector steer = new Vector(desired.sub(this.velocity)[0],desired.sub(this.velocity)[1]);
        steer.limit(max_force);
        //this.acceleration.add(steer);

        return steer;
    }
//    void applyForce(double force) {
//        // We could add mass here if we want A = F / M
//        this.acceleration.add(double force);
//    }

    void update() {
        this.position.add(this.velocity);
        this.velocity.add(this.acceleration);
        this.velocity.limit(maxSpeed);
    }

    void edges() {
        if(this.position.xvalue > BoidRunner.WIDTH)
            this.position.xvalue = 0;
        else if(this.position.xvalue < 0)
            this.position.xvalue = BoidRunner.WIDTH;

        if(this.position.yvalue > BoidRunner.HEIGHT)
            this.position.yvalue = 0;
        else if(this.position.yvalue < 0)
            this.position.yvalue = BoidRunner.HEIGHT;
    }

    double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2-y1), 2));
    }

    void wander() {
        // Создаем вектор с случайным углом направления
        double angle = Math.random() * 2 * Math.PI;
        Vector randomForce = new Vector(Math.cos(angle), Math.sin(angle));
        randomForce.multiply(max_force); // Умножаем на максимальную силу, чтобы получить изменение ускорения
        this.acceleration.add(randomForce); // Добавляем случайное ускорение к текущему ускорению
    }

    public void draw(Graphics2D g) {
        AffineTransform save = g.getTransform();
        g.translate((int)this.position.xvalue, (int)this.position.yvalue);
            g.rotate(this.velocity.dir() + Math.PI/2);

        // Рисуем радиус восприятия хорошей еды
        g.setColor(Color.GREEN);
        g.drawOval(-dna[2], -dna[2], dna[2]*2, dna[2]*2);

        g.drawLine(0, 0, 0, -dna[0]*100); // Линия привлекательности хорошей еды

        //g.drawLine(0, 0, 0, -100); // Линия привлекательности хорошей еды

        // Рисуем радиус восприятия яда
        g.setColor(Color.RED);
        g.drawOval(-dna[3], -dna[3], dna[3]*2, dna[3]*2);

        // Рисуем линии привлекательности
        g.drawLine(0, 0, 0, dna[1]*100); // Линия привлекательности яда

        g.setColor(Color.WHITE);
        g.fill(shape);
        g.draw(shape);







        g.setTransform(save);
    }
    static double maxSpeed = 2;
    static double  max_force = 0.5;
    int food_value = 1;   // health gained from a food
    int poison_value = -2;   // health lost from a poison
}