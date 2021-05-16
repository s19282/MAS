package com.company;

public class WaterVehicle extends Vehicle
{
    private int displacement;

    public WaterVehicle(String make, String model, int year, VehicleType drive, int engineCapacity, int displacement) {
        super(make, model, year, drive, engineCapacity);
        this.displacement = displacement;
    }

    public WaterVehicle(String make, String model, int year, VehicleType drive, double sailSpan, int displacement) {
        super(make, model, year, drive, sailSpan);
        this.displacement = displacement;
    }

    public int getDisplacement() {
        return displacement;
    }

    public void setDisplacement(int displacement) {
        this.displacement = displacement;
    }

    @Override
    public String toString()
    {
        return "WaterVehicle{" +
                super.toString() +
                "displacement=" + displacement +
                '}';
    }
}
