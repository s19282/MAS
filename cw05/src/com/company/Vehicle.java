package com.company;

import java.util.ArrayList;
import java.util.List;

public class Vehicle
{
    private String make;
    private String model;
    private String licencePlateNumber;
    private final List<Rent> rents = new ArrayList<>();

    public Vehicle(String make, String model, String licencePlateNumber)
    {
        this.make = make;
        this.model = model;
        this.licencePlateNumber = licencePlateNumber;
    }

    public String getMake() {
        return make;
    }

    public List<Rent> getRents() {
        return rents;
    }

    public void addRent(Rent rent)
    {
        if(!rents.contains(rent))
        {
            rents.add(rent);
            rent.setVehicle(this);
        }
    }
    void removeRent(Rent rent)
    {
        rents.remove(rent);
        if(rent.getClient().getRents().contains(rent))
        {
            rent.getClient().removeRent(rent);
        }
        rent.destroy();
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

    public String getLicencePlateNumber() {
        return licencePlateNumber;
    }

    public void setLicencePlateNumber(String licencePlateNumber) {
        this.licencePlateNumber = licencePlateNumber;
    }
    public String showVehicle()
    {
        return "Vehicle{" +
            "make='" + make +
            ", model='" + model +
            ", licencePlateNumber='" + licencePlateNumber +
            "}";
    }

    @Override
    public String toString() {
        StringBuilder rentsString = new StringBuilder();
        for (Rent rent : rents) rentsString.append(rent.showClientOnly());

        return "Vehicle{" +
                "make='" + make +
                ", model='" + model +
                ", licencePlateNumber='" + licencePlateNumber +
                ", rents=[" + rentsString +
                "}";
    }
}
