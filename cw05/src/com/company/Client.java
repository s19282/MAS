package com.company;

import java.util.ArrayList;
import java.util.List;

public class Client
{
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private List<Rent> rents = new ArrayList<>();

    public Client(String firstName, String lastName, int phoneNumber)
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
            rent.setClient(this);
        }
    }
    void removeRent(Rent rent)
    {
        rents.remove(rent);
        if(rent.getVehicle().getRents().contains(rent))
        {
            rent.getVehicle().removeRent(rent);
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
    public String showClient()
    {
        return "Client{" +
                "firstName='" + firstName +
                ", lastName='" + lastName +
                ", phoneNumber=" + phoneNumber +
                "}";
    }

    @Override
    public String toString() {
        StringBuilder rentsString = new StringBuilder();
        for (Rent rent : rents) rentsString.append(rent.showVehicleOnly());

        return "Client{" +
                "firstName=" + firstName +
                ", lastName=" + lastName +
                ", phoneNumber=" + phoneNumber +
                ", rents=[" + rentsString +
                "}";
    }
}
