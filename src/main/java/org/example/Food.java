package org.example;

import java.awt.*;

public class Food {
    Vector position;
    Color color;

    public Food(boolean poison) {
        this.position = new Vector((Math.random()*BoidRunner.WIDTH),(Math.random()*BoidRunner.HEIGHT));
        if(poison){
            this.color=new Color(255, 0, 0);
        }else {
            this.color=new Color(0, 255, 0);

        }
    }

    public void draw(Graphics2D g) {
        int radius = 5; // Радиус круга
        g.setColor(color);
        // Вычитаем радиус из координат, чтобы центрировать круг
        g.fillOval((int)this.position.getXValue() - radius, (int)this.position.getYValue() - radius, radius * 2, radius * 2);


    }


}
