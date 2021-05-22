package com.company;

import java.util.ArrayList;
import java.util.List;

public class Person
{
    private String firstName;
    private String lastName;
    private int phoneNumber;

    private final List<Sponsor> sponsors = new ArrayList<>();

    public Person(String firstName, String lastName, int phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public void sponsor(Sponsor sponsor) throws Exception {
        if(!sponsors.contains(sponsor))
        {
            sponsors.add(sponsor);
            sponsor.setSponsor(this);
        }
    }

    public List<Sponsor> getSponsors() {
        return sponsors;
    }

    public void removeSponsor(Sponsor sponsor)
    {
        if(sponsors.contains(sponsor))
        {
            sponsors.remove(sponsor);
            sponsor.removeSponsor(this);
        }
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
    public String showPerson()
    {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sponsors.forEach(i->sb.append(i.showSponsor()));
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", sponsors=" + sb +
                '}';
    }
}
