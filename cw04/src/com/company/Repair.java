package com.company;

import java.time.LocalTime;

public class Repair
{
    private String partName;
    private String description;
    private LocalTime duration;
    private RepairState state;

    public Repair(String partName, String description, LocalTime duration, RepairState state) {
        this.partName = partName;
        this.description = description;
        this.duration = duration;
        this.state = state;
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

    public LocalTime getDuration() {
        return duration;
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
}
