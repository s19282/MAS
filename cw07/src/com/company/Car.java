package com.company;

import java.util.ArrayList;
import java.util.List;

public class Car
{
    private String make;
    private String model;
    private String licencePlateNumber;
    private final List<Rent> rents = new ArrayList<>();

    public Car(String make, String model, String licencePlateNumber)
    {
        setMake(make);
        setModel(model);
        setLicencePlateNumber(licencePlateNumber);
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
            rent.setCar(this);
        }
    }
    void removeRent(Rent rent)
    {
        rents.remove(rent);
        if(rent.getCustomer().getRents().contains(rent))
        {
            rent.getCustomer().removeRent(rent);
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
    public String showCar()
    {
        return "Car{" +
                "make='" + getMake() +
                ", model='" + getModel() +
                ", licencePlateNumber='" + getLicencePlateNumber() +
                "}";
    }

    @Override
    public String toString() {
        StringBuilder rentsString = new StringBuilder();
        for (Rent rent : rents) rentsString.append(rent.showCustomerOnly());

        return "Car{" +
                "make='" + getMake() +
                ", model='" + getModel() +
                ", licencePlateNumber='" + getLicencePlateNumber() +
                ", rents=[" + rentsString +
                "}";
    }
}