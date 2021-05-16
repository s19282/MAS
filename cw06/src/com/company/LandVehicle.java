package com.company;

public class LandVehicle extends Vehicle
{
    private int numberOfWheels;

    public LandVehicle(String make, String model, int year, VehicleType drive, int engineCapacity, int numberOfWheels) {
        super(make, model, year, drive, engineCapacity);
        this.numberOfWheels = numberOfWheels;
    }

    public LandVehicle(String make, String model, int year, VehicleType drive, double sailSpan, int numberOfWheels) {
        super(make, model, year, drive, sailSpan);
        this.numberOfWheels = numberOfWheels;
    }

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    public void setNumberOfWheels(int numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }

    @Override
    public String toString() {
        return "LandVehicle{" +
                super.toString() +
                "numberOfWheels=" + numberOfWheels +
                '}';
    }
}
