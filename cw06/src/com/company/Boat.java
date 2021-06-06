package com.company;

public class Boat implements Swimmable
{
    private String make;
    private String model;
    private int displacement;
    private int numberOfSeats;

    public Boat(String make, String model, int displacement, int numberOfSeats) {
        setMake(make);
        setModel(model);
        setDisplacement(displacement);
        setNumberOfSeats(numberOfSeats);
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
}
