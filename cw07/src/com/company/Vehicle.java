package com.company;

import java.util.HashMap;
import java.util.Map;

public class Vehicle
{
    private String make;
    private String model;
    private String VIN;

    private static final Map<String,Vehicle> map = new HashMap<>();

    public Vehicle(String make, String model, String VIN) throws Exception {
        setMake(make);
        setModel(model);
        setVIN(VIN);
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) throws Exception {
        if(map.containsKey(VIN))
        {
            throw new Exception("Vehicle with this VIN exist already!");
        }
        this.VIN = VIN;
        map.put(getVIN(),this);
    }
    public static Vehicle findByVIN(String VIN)
    {
        return map.get(VIN);
    }
    public void removeVehicle(String VIN)
    {
        map.remove(VIN);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "make='" + getMake() + '\'' +
                ", model='" + getModel() + '\'' +
                ", VIN='" + getVIN() + '\'' +
                '}';
    }
}
