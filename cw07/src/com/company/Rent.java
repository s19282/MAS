package com.company;

import java.time.LocalDate;

public class Rent
{
    private LocalDate dateSince;
    private LocalDate dateTo;
    private Double hourlyRate;
    private Car car;
    private Customer customer;

    public Rent(LocalDate dateSince, LocalDate dateTo, Double hourlyRate, Car car, Customer customer)
    {
        setDateSince(dateSince);
        setDateTo(dateTo);
        setHourlyRate(hourlyRate);
        setCar(car);
        setCustomer(customer);
    }

    public void setCar(Car car)
    {
        if(this.car != null)
        {
            if(this.car != car)
            {
                this.car.removeRent(this);
                this.car = car;
                car.addRent(this);
            }
        }
        else
        {
            this.car = car;
            car.addRent(this);
        }
    }

    public void setCustomer(Customer customer)
    {
        if(this.customer != null)
        {
            if(this.customer != customer)
            {
                this.customer.removeRent(this);
                this.customer = customer;
                customer.addRent(this);
            }
        }
        else
        {
            this.customer = customer;
            customer.addRent(this);
        }
    }
    public void destroy()
    {
        this.dateSince = null;
        this.dateTo = null;
        this.hourlyRate = null;
        this.car = null;
        this.customer = null;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getDateSince() {
        return dateSince;
    }

    public void setDateSince(LocalDate dateSince) {
        this.dateSince = dateSince;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String showCarOnly()
    {
        return "Rent{" +
                "dateSince=" + getDateSince() +
                ", dateTo=" + getDateTo() +
                ", hourlyRate=" + getHourlyRate() +
                ", car=" + car.showCar() +
                "}";
    }
    public String showCustomerOnly()
    {
        return "Rent{" +
                "dateSince=" + getDateSince() +
                ", dateTo=" + getDateTo() +
                ", hourlyRate=" + getHourlyRate() +
                ", customer=" + customer.showCustomer() +
                "}";
    }

    @Override
    public String toString() {
        return "Rent{" +
                "dateSince=" + (dateSince != null ? dateSince : "null") +
                ", dateTo=" + (dateTo != null ? dateTo : "null") +
                ", hourlyRate=" + (hourlyRate != null ? hourlyRate : "null") +
                ", car=" + (car != null ? car.showCar() : "null") +
                ", customer=" + (customer != null ? customer.showCustomer() : "null") +
                "}";
    }
}