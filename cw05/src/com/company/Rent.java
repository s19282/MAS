package com.company;

import java.time.LocalDate;

public class Rent
{
    private LocalDate dateSince;
    private LocalDate dateTo;
    private Double hourlyRate;
    private Vehicle vehicle;
    private Client client;

    public Rent(LocalDate dateSince, LocalDate dateTo, Double hourlyRate, Vehicle vehicle, Client client)
    {
        this.dateSince = dateSince;
        this.dateTo = dateTo;
        this.hourlyRate = hourlyRate;
        setVehicle(vehicle);
        setClient(client);
    }

    void setVehicle(Vehicle vehicle)
    {
        if(this.vehicle != null)
        {
            if(this.vehicle != vehicle)
            {
                this.vehicle.removeRent(this);
                this.vehicle = vehicle;
                vehicle.addRent(this);
            }
        }
        else
        {
            this.vehicle = vehicle;
            vehicle.addRent(this);
        }
    }

    void setClient(Client client)
    {
        if(this.client != null)
        {
            if(this.client != client)
            {
                this.client.removeRent(this);
                this.client = client;
                client.addRent(this);
            }
        }
        else
        {
            this.client = client;
            client.addRent(this);
        }
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Client getClient() {
        return client;
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

    public String showVehicleOnly()
    {
        return "\n\tRent\n\t{" + "\n" +
                "\t\tdateSince=" + dateSince + "\n" +
                "\t\tdateTo=" + dateTo + "\n" +
                "\t\thourlyRate=" + hourlyRate + "\n" +
                "\t\tvehicle=" + vehicle.showVehicle() + "\n" +
                "\t}";
    }
    public String showClientOnly()
    {
        return "\n\tRent\n\t{" + "\n" +
                "\t\tdateSince=" + dateSince + "\n" +
                "\t\tdateTo=" + dateTo + "\n" +
                "\t\thourlyRate=" + hourlyRate + "\n" +
                "\t\tclient=" + client.showClient() + "\n" +
                "\t}";
    }

    @Override
    public String toString() {
        return "Rent\n{" + "\n" +
                "\tdateSince=" + dateSince + "\n" +
                "\tdateTo=" + dateTo + "\n" +
                "\thourlyRate=" + hourlyRate + "\n" +
                "\tvehicle=" + vehicle.showVehicle() + "\n" +
                "\tclient=" + client.showClient() + "\n" +
                "}";
    }
}
