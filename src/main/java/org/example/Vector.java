package org.example;

// Класс Vector для представления векторов в двумерном пространстве
public class Vector {
    public double xvalue;
    public double yvalue;

    public Vector() {
        this.xvalue = Math.random()-0.5;
        this.yvalue = Math.random()-0.5;
    }

    double dist(Vector second){
        double distance = Math.sqrt(Math.pow(second.getXValue() - this.getXValue(), 2) + Math.pow(second.getYValue() - this.getYValue(), 2));
        return distance;

    }


    public Vector(double xvalue, double yvalue) {
        this.xvalue = xvalue;
        this.yvalue = yvalue;
    }

    public void set(double xvalue, double yvalue) {
        this.xvalue = xvalue;
        this.yvalue = yvalue;
    }

    public double getXValue() { return this.xvalue; }
    public double getYValue() { return this.yvalue; }

    public void setXValue(double newValue) { this.xvalue = newValue; }
    public void setYValue(double newValue) { this.yvalue = newValue; }

    public double getMagnitude() {
        return Math.sqrt(Math.pow(this.xvalue, 2) + Math.pow(this.yvalue, 2));
    }

    public void limit(double maxForce) {
        double magnitude = Math.sqrt(Math.pow(this.xvalue, 2) + Math.pow(this.yvalue, 2));
        double multiplier;
        if(magnitude > maxForce)
            multiplier = maxForce / magnitude;
        else
            multiplier = 1.0;

        this.xvalue *= multiplier;
        this.yvalue *= multiplier;
    }

    // увеличиваем скорость без изменения направления движения
    public Vector setMagnitude(double newMagnitude) {
        double currentMagnitude = Math.sqrt(Math.pow(this.xvalue, 2) + Math.pow(this.yvalue, 2));
        this.xvalue *= (newMagnitude/currentMagnitude);
        this.yvalue *= (newMagnitude/currentMagnitude);
        return this;
    }

    void add(Vector parent) {
        this.xvalue += parent.getXValue();
        this.yvalue += parent.getYValue();
    }

    void subtract(Vector parent) {
        this.xvalue -= parent.getXValue();
        this.yvalue -= parent.getYValue();
    }

    double[] sub(Vector second) {
        double[] arr = new double[2];
        arr[0]=this.getXValue() - second.getXValue();
        arr[1]=this.getYValue() - second.getYValue();
        return arr;
    }

    void multiply(double multiplier) {
        this.xvalue *= multiplier;
        this.yvalue *= multiplier;
    }

    void divide(double denominator) {
        this.xvalue /= denominator;
        this.yvalue /= denominator;
    }

    double dir() {
        return Math.atan2(this.yvalue, this.xvalue);
    }

    void setValues(double xvalue, double yvalue) {
        this.xvalue = xvalue;
        this.yvalue = yvalue;
    }

    double movement() {
        return this.xvalue+this.yvalue;
    }
}