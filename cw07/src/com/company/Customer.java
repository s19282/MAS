package com.company;

import java.util.ArrayList;
import java.util.List;

public class Customer
{
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private final List<Rent> rents = new ArrayList<>();

    public Customer(String firstName, String lastName, int phoneNumber)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public List<Rent> getRents() {
        return rents;
    }

    public void addRent(Rent rent)
    {
        if(!rents.contains(rent))
        {
            rents.add(rent);
            rent.setCustomer(this);
        }
    }
    void removeRent(Rent rent)
    {
        rents.remove(rent);
        if(rent.getCar().getRents().contains(rent))
        {
            rent.getCar().removeRent(rent);
        }
        rent.destroy();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String showCustomer()
    {
        return "Customer{" +
                "firstName='" + firstName +
                ", lastName='" + lastName +
                ", phoneNumber=" + phoneNumber +
                "}";
    }

    @Override
    public String toString() {
        StringBuilder rentsString = new StringBuilder();
        for (Rent rent : rents) rentsString.append(rent.showCarOnly());

        return "Customer{" +
                "firstName=" + firstName +
                ", lastName=" + lastName +
                ", phoneNumber=" + phoneNumber +
                ", rents=[" + rentsString +
                "}";
    }
}