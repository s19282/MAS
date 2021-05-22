package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarPart
{
    private String name;
    private Double price;
    private String serialNumber;

    public CarPart(String name, Double price, String serialNumber) throws Exception {
        setName(name);
        setPrice(price);
        setSerialNumber(serialNumber);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) throws Exception {
        Pattern pattern = Pattern.compile("^[a-zA-Z]{3}-\\d{5}$");
        Matcher matcher = pattern.matcher(serialNumber);
        if (!matcher.matches())
        {
            throw new Exception("Serial number is incorrect!");
        }
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "CarPart{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }
}
