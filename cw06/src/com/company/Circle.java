package com.company;

public class Circle extends Shape
{
    private int radius;

    public Circle(String color, int borderWidth, int radius)
    {
        super(color, borderWidth);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI*radius*radius;
    }
}
