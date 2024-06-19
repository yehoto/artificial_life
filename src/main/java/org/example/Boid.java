package org.example;


import java.awt.*;
import java.util.ArrayList;
import java.awt.geom.*;

// Класс Boid, представляющий отдельного агента в стае
class Boid {
    Vector position;
    Vector velocity;
    Vector acceleration;
    static final int MAX_FORCE = 10; // Максимальная сила, которая может быть приложена к Boid
    static final int MAX_SPEED = 5; // Максимальная скорость Boid

    public Boid(int x, int y) {
        position = new Vector(x, y);
        velocity = Vector.random();
        acceleration = new Vector(0, 0);
    }

    public void update() {
        position.add(velocity);
        velocity.add(acceleration);
        velocity.limit(MAX_SPEED);
        acceleration.multiply(0); // Сброс ускорения
    }

    public void applyForce(Vector force) {
        acceleration.add(force);
    }

    public void edges() {
        if (position.x > BoidRunner.WIDTH) position.x = 0;
        if (position.x < 0) position.x = BoidRunner.WIDTH;
        if (position.y > BoidRunner.HEIGHT) position.y = 0;
        if (position.y < 0) position.y = BoidRunner.HEIGHT;
    }

    public void flock(ArrayList<Boid> boids) {
        Vector separation = separate(boids); // Получаем вектор разделения
        separation.multiply(1.5f); // Увеличиваем важность разделения

        applyForce(separation);
    }
    // Метод для поддержания дистанции между Boids
    private Vector separate(ArrayList<Boid> boids) {
        float desiredSeparation = 25.0f;
        Vector steer = new Vector(0, 0);
        int count = 0;
        for (Boid other : boids) {
            float d = Vector.dist(position, other.position);
            if ((d > 0) && (d < desiredSeparation)) {
                Vector diff = Vector.sub(position, other.position);
                diff.normalize();
                diff.divide(d); // Вес по обратной пропорции к расстоянию
                steer.add(diff);
                count++;
            }
        }
        if (count > 0) {
            steer.divide((float)count);
        }

        if (steer.magnitude() > 0) {
            steer.normalize();
            steer.multiply(MAX_SPEED);
            steer = Vector.sub(steer, velocity); // Исправленный вызов метода sub
            steer.limit(MAX_FORCE);
        }
        return steer;
    }


    public void draw(Graphics2D g) {
        g.fillOval((int)position.x, (int)position.y, 10, 10);
    }
}
