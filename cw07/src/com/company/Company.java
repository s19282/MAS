package com.company;

import java.util.ArrayList;
import java.util.List;

public class Company
{
    private String name;
    private int REGON;
    private int phoneNumber;

    private final List<Sponsor> sponsors = new ArrayList<>();

    public Company(String name, int REGON, int phoneNumber) {
        setName(name);
        setREGON(REGON);
        setPhoneNumber(phoneNumber);
    }

    public void sponsor(Sponsor sponsor) throws Exception {
        if(!sponsors.contains(sponsor))
        {
            sponsors.add(sponsor);
            sponsor.setSponsor(this);
        }
    }

    public void removeSponsor(Sponsor sponsor)
    {
        if(sponsors.contains(sponsor))
        {
            sponsors.remove(sponsor);
            sponsor.removeSponsor(this);
        }
    }

    public List<Sponsor> getSponsors() {
        return sponsors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getREGON() {
        return REGON;
    }

    public void setREGON(int REGON) {
        this.REGON = REGON;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String showCompany()
    {
        return "Company{" +
                "name='" + getName() + '\'' +
                ", REGON=" + getREGON() +
                ", phoneNumber=" + getPhoneNumber() +
                '}';
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sponsors.forEach(i->sb.append(i.showSponsor()));

        return "Company{" +
                "name='" + getName() + '\'' +
                ", REGON=" + getREGON() +
                ", phoneNumber=" + getPhoneNumber() +
                ", sponsors=" + sb +
                '}';
    }
}
