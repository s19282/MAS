package com.company;

public class Boat
{
    private String make;
    private String model;
    private int displacement;

    public Boat(String make, String model, int displacement)
    {
        this.make = make;
        this.model = model;
        this.displacement = displacement;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getDisplacement() {
        return displacement;
    }

    public void setDisplacement(int displacement) {
        this.displacement = displacement;
    }
}
