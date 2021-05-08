package com.company;

import java.io.Serializable;
import java.time.LocalTime;

public class Repair implements Serializable
{
    private String partName;
    private String description;
    private LocalTime duration;
    private RepairState state;
    private Double partsCost;
    private Visit visit;

    public Repair(String partName, String description, LocalTime duration, RepairState state, Double partsCost)
    {
        this.partName = partName;
        this.description = description;
        this.duration = duration;
        this.state = state;
        this.partsCost = partsCost;
    }

    public Repair(LocalTime duration, Double partsCost)
    {
        this.duration = duration;
        this.partsCost = partsCost;
    }

    public void setVisit(Visit visit)
    {
        if(this.visit != null)
        {
            if(this.visit != visit)
            {
                this.visit.removeRepair(this);
                this.visit = visit;
                visit.addRepair(this);
            }
        }
        else
        {
            this.visit = visit;
            visit.addRepair(this);
        }
    }

    public void removeVisit()
    {
        if(visit.getRepairs().contains(this))
            visit.removeRepair(this);

        if(visit !=null)
        {
            visit = null;
        }

    }

    public Visit getVisit() {
        return visit;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration.getHour();
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public RepairState getState() {
        return state;
    }

    public void setState(RepairState state) {
        this.state = state;
    }

    public Double getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(Double partsCost) {
        this.partsCost = partsCost;
    }

    public String showRepair()
    {
        return "Repair{" +
                "partName=" + partName +
                ", description=" + description +
                ", duration=" + duration +
                ", state=" + state +
                ", partsCost=" + partsCost +
                ", visit=" + (visit!=null ? visit.showVisit() : "null") +
                "}";
    }

    @Override
    public String toString() {
        return "Repair{" +
                "partName=" + partName +
                ", description=" + description +
                ", duration=" + duration +
                ", state=" + state +
                ", partsCost=" + partsCost +
                "}";
    }
}
