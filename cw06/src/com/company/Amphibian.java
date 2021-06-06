package com.company;

public class Amphibian extends Car implements Swimmable
{
    private int maxSpeed;
    private int displacement;
    private int numberOfSeats;

    public Amphibian(String make, String model, int numberOfWheels, int maxSpeed, int displacement, int numberOfSeats) {
        super(make, model, numberOfWheels);
        setMaxSpeed(maxSpeed);
        setDisplacement(displacement);
        setNumberOfSeats(numberOfSeats);
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    @Override
    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public int getDisplacement() {
        return displacement;
    }

    @Override
    public void setDisplacement(int displacement) {
        this.displacement = displacement;
    }

    @Override
    public String toString() {
        return "Amphibian{" +
                "maxSpeed=" + maxSpeed +
                ", displacement=" + displacement +
                ", numberOfSeats=" + numberOfSeats +
                super.toString() +
                '}';
    }
}
