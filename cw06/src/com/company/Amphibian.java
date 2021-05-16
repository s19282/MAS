package com.company;

public class Amphibian extends Car implements Swimmable
{
    private int maxSpeed;

    public Amphibian(String make, String model, int numberOfWheels, int maxSpeed) {
        super(make, model, numberOfWheels);
        this.maxSpeed = maxSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public void setMake() {

    }

    @Override
    public void setModel() {

    }

    @Override
    public int getDisplacement() {
        return 0;
    }

    @Override
    public void setDisplacement() {

    }
}
