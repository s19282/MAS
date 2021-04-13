package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Visit implements Serializable {
    private LocalDate startDate;
    private LocalDate expectedEndDate;
    private LocalDate endDate;
    private Double estimatedCost;
    private Double cost;
    private List<Repair> repairs;
    private static Double manHourCost = 200D;
    private static List<Visit> extent = new ArrayList<>();

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }

    public Visit(LocalDate startDate, LocalDate expectedEndDate, LocalDate endDate, Double estimatedCost, Double cost, List<Repair> repairs) {
        this.startDate = startDate;
        this.expectedEndDate = expectedEndDate;
        this.endDate = endDate;
        this.estimatedCost = estimatedCost;
        this.cost = cost;
        this.repairs = repairs;
        addVisit(this);
    }

    public Visit(LocalDate startDate, LocalDate expectedEndDate, Double estimatedCost, List<Repair> repairs) {
        this.startDate = startDate;
        this.expectedEndDate = expectedEndDate;
        this.estimatedCost = estimatedCost;
        this.repairs = repairs;
        addVisit(this);
    }

    public static List<Visit> getVisits(LocalDate dateSince, LocalDate dateTo)
    {
        return  extent.stream().filter(visit -> visit.startDate.isAfter(dateSince.minusDays(1)) && visit.startDate.isBefore(dateTo.plusDays(1))).collect(Collectors.toList());
    }
    public static List<Visit> getVisits(LocalDate dateSince)
    {
        return  extent.stream().filter(visit -> visit.startDate.isAfter(dateSince.minusDays(1))).collect(Collectors.toList());
    }
    public void remove()
    {
        removeVisit(this);
    }
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getExpectedEndDate() {
        return expectedEndDate;
    }

    public void setExpectedEndDate(LocalDate expectedEndDate) {
        this.expectedEndDate = expectedEndDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(Double estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public Double getCost() 
    {
        double sum = 0D;
        for (Repair r : repairs)
        {
            sum += r.getDuration() * manHourCost + r.getPartsCost();
        }
        return sum;
    }

    public void setCost() {
        cost = 0D;
        for (Repair r : repairs)
        {
            cost += r.getDuration() * manHourCost + r.getPartsCost();
        }
    }

    public static Double getManHourCost() {
        return manHourCost;
    }

    public static void setManHourCost(Double manHourCost) {
        Visit.manHourCost = manHourCost;
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
        if (extent.size()==0)
        {
            System.out.println("Extent is empty");
        }
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
                ", endDate=" + (getEndDate() != null ? getEndDate() : "End date not set") +
                ", estimatedCost=" + estimatedCost +
                ", cost=" + (getCost() != null ? getCost() : "Cost not set") +
                '}';
    }
}
