package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Visit implements Serializable
{
    LocalDate startDate;
    LocalDate expectedEndDate;
    Optional<LocalDate> endDate = Optional.empty();
//    TODO: replace optional with standard reference
    Double estimatedCost;
    Optional<Double> cost = Optional.empty();
    static Double manHourCost = 200D;
    private static List<Visit> extent = new ArrayList<>();

    public Visit(LocalDate startDate, LocalDate expectedEndDate, LocalDate endDate, Double estimatedCost, Double cost) {
        this.startDate = startDate;
        this.expectedEndDate = expectedEndDate;
        this.endDate = Optional.ofNullable(endDate);
        this.estimatedCost = estimatedCost;
        this.cost = Optional.ofNullable(cost);
        addVisit(this);
    }

    public Visit(LocalDate startDate, LocalDate expectedEndDate, Double estimatedCost) {
        this.startDate = startDate;
        this.expectedEndDate = expectedEndDate;
        this.estimatedCost = estimatedCost;
        addVisit(this);
    }

    public Optional<LocalDate> getEndDate() {
        return endDate;
    }

    public Optional<Double> getCost() {
        return cost;
    }

    private static void addVisit(Visit visit)
    {
        extent.add(visit);
    }
    private static void removeVisit(Visit visit)
    {
        extent.remove(visit);
    }
    public static void showExtent()
    {
        System.out.println("Extent of the class: " + Visit.class.getName());
        for(Visit visit : extent)
        {
            System.out.println(visit);
        }
    }
    public static void writeExtent(ObjectOutputStream stream) throws IOException
    {
        stream.writeObject(extent);
    }
    public static void readExtent(ObjectInputStream stream) throws  IOException, ClassNotFoundException
    {
        extent = (ArrayList<Visit>) stream.readObject();
    }

    @Override
    public String toString() {
        return "Visit{" +
                "startDate=" + startDate +
                ", expectedEndDate=" + expectedEndDate +
                ", endDate=" + (getEndDate().isPresent() ? getEndDate() : "End date not set") +
                ", estimatedCost=" + estimatedCost +
                ", cost=" + (getCost().isPresent() ? getCost() : "Cost not set") +
                '}';
    }
}
