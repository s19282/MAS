package com.company;

import java.util.ArrayList;
import java.util.List;

public class Device {
    private String make;
    private String mode;
    private double price;

    private final List<Sensor> sensors = new ArrayList<>();
    private final List<Sensor> criticalSensors = new ArrayList<>();

    public Device(String make, String mode, double price) {
        this.make = make;
        this.mode = mode;
        this.price = price;
    }

    public void addSensor(Sensor sensor) {
        if (!sensors.contains(sensor)) {
            sensors.add(sensor);
            sensor.setDevice(this);
        }
    }

    public void removeSensor(Sensor sensor) {
        if (sensors.contains(sensor)) {
            sensors.remove(sensor);
            sensor.removeDevice();
        }
    }

    public void addCriticalSensor(Sensor sensor) throws Exception {
        if (!sensors.contains(sensor) || sensor.getDevice() == null) {
            throw new Exception("Add Sensor before you set it critical!");
        } else {
            if (!criticalSensors.contains(sensor)) {
                criticalSensors.add(sensor);
                sensor.setDeviceSubset(this);
            }
        }
    }

    public void removeCriticalSensor(Sensor sensor) {
        if (criticalSensors.contains(sensor)) {
            criticalSensors.remove(sensor);
            sensor.removeDeviceSubset();
        }
    }

    public List<Sensor> getCriticalSensors() {
        return criticalSensors;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public String showDevice() {
        return "Device{" +
                "make='" + make + '\'' +
                ", mode='" + mode + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sensors.forEach(i -> sb.append(i.showSensor()));
        StringBuilder sb2 = new StringBuilder();
        criticalSensors.forEach(i->sb2.append(i.showSensor()));

        return "Device{" +
                "make='" + make + '\'' +
                ", mode='" + mode + '\'' +
                ", price='" + price + '\'' +
                ", sensors=" + sb +
                ", criticalSensors=" + sb2 +
                '}';
    }
}
