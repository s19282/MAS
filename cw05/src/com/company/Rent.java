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
    void destroy()
    {
        this.dateSince = null;
        this.dateTo = null;
        this.hourlyRate = null;
        this.vehicle = null;
        this.client = null;
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
        return "Rent{" +
                "dateSince=" + dateSince +
                ", dateTo=" + dateTo +
                ", hourlyRate=" + hourlyRate +
                ", vehicle=" + vehicle.showVehicle() +
                "}";
    }
    public String showClientOnly()
    {
        return "Rent{" +
                "dateSince=" + dateSince +
                ", dateTo=" + dateTo +
                ", hourlyRate=" + hourlyRate +
                ", client=" + client.showClient() +
                "}";
    }

    @Override
    public String toString() {
        return "Rent{" +
                "dateSince=" + (dateSince != null ? dateSince : "null") +
                ", dateTo=" + (dateTo != null ? dateTo : "null") +
                ", hourlyRate=" + (hourlyRate != null ? hourlyRate : "null") +
                ", vehicle=" + (vehicle != null ? vehicle.showVehicle() : "null") +
                ", client=" + (client != null ? client.showClient() : "null") +
                "}";
    }
}
