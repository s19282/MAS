package com.company;

public class Car
{
    private String make;
    private String model;
    private int numberOfWheels;

    public Car(String make, String model, int numberOfWheels)
    {
        this.make = make;
        this.model = model;
        this.numberOfWheels = numberOfWheels;
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

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    public void setNumberOfWheels(int numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }
}
