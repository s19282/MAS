package com.company;

public class Product
{
    String name;
    double price;

    public Product(String name, double price)
    {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Name: "+name+" Price: "+price;
    }
}
