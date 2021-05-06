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
        return "Vehicle\n\t\t{" + "\n" +
            "\t\t\tmake='" + make + '\n' +
            "\t\t\tmodel='" + model + '\n' +
            "\t\t\tlicencePlateNumber='" + licencePlateNumber + '\n' +
            "\t\t}";
    }

    @Override
    public String toString() {
        StringBuilder rentsString = new StringBuilder();
        for (Rent rent : rents) rentsString.append(rent.showClientOnly());

        return "Vehicle\n{" + "\n" +
                "\tmake='" + make + '\n' +
                "\tmodel='" + model + '\n' +
                "\tlicencePlateNumber='" + licencePlateNumber + '\n' +
                "\trents=[" + rentsString + "]\n" +
                "}";
    }
}
