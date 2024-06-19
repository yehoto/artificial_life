package org.example;

// Класс Vector для представления векторов в двумерном пространстве
class Vector {
    float x, y;

    Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    // Добавление другого вектора к этому вектору
    public void add(Vector v) {
        x += v.x;
        y += v.y;
    }

    // Вычитание другого вектора из этого вектора
    // Статический метод для вычитания одного вектора из другого
    public static Vector sub(Vector v1, Vector v2) {
        return new Vector(v1.x - v2.x, v1.y - v2.y);
    }

    // Умножение вектора на скаляр
    public void multiply(float n) {
        x *= n;
        y *= n;
    }

    // Деление вектора на скаляр
    public void divide(float n) {
        x /= n;
        y /= n;
    }

    // Ограничение длины вектора максимальным значением
    public void limit(float max) {
        if (magnitude() > max) {
            normalize();
            multiply(max);
        }
    }

    // Нормализация вектора до единичной длины
    public void normalize() {
        float m = magnitude();
        if (m != 0) {
            divide(m);
        }
    }

    // Вычисление длины вектора
    public float magnitude() {
        return (float)Math.sqrt(x * x + y * y);
    }

    // Статический метод для вычисления расстояния между двумя векторами
    public static float dist(Vector v1, Vector v2) {
        float dx = v1.x - v2.x;
        float dy = v1.y - v2.y;
        return (float)Math.sqrt(dx * dx + dy * dy);
    }

    // Статический метод для создания случайного вектора
    public static Vector random() {
        return new Vector((float)Math.random() * 2 - 1, (float)Math.random() * 2 - 1);
    }
}

