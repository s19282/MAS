package com.company;

public abstract class Shape
{
    private String color;
    private int borderWidth;

    public Shape(String color, int borderWidth) {
        this.color = color;
        this.borderWidth = borderWidth;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public abstract double getArea();
}
