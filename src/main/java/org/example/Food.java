package org.example;

import java.awt.*;

public class Food {
    Vector position;
    Color color;
    // Добавляем поля для "дрожания"
    double shakeMagnitude ; // Максимальное смещение

    public Food(boolean poison) {
        this.position = new Vector((Math.random()*BoidRunner.WIDTH),(Math.random()*BoidRunner.HEIGHT));
        this.shakeMagnitude = Math.random() * 2.0; // Значение от 0.0 до 2.0
        if(poison){
            this.color=new Color(255, 0, 0);
        }else {
            this.color=new Color(0, 255, 0);

        }
    }

//    public void draw(Graphics2D g) {
//        int radius = 5; // Радиус круга
//        g.setColor(color);
//        // Вычитаем радиус из координат, чтобы центрировать круг
//        g.fillOval((int)this.position.getXValue() - radius, (int)this.position.getYValue() - radius, radius * 2, radius * 2);
//
//
//    }

    public void draw(Graphics2D g) {
        int radius = 5; // Радиус круга
        g.setColor(color);
        // Добавляем небольшое случайное смещение к позиции
        double shakeX = (Math.random() - 0.5) * shakeMagnitude;
        double shakeY = (Math.random() - 0.5) * shakeMagnitude;
        g.fillOval((int)(this.position.getXValue() - radius + shakeX),
                (int)(this.position.getYValue() - radius + shakeY),
                radius * 2, radius * 2);
    }


}
