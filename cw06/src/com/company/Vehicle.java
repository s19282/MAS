package com.company;

enum VehicleType {WIND_POWERED,ENGINE_POWERED}

public abstract class Vehicle
{
    private String make;
    private String model;
    private int year;

    private VehicleType drive;
    private Double sailSpan;
    private Integer engineCapacity;

    public Vehicle(String make, String model, int year, VehicleType drive, int engineCapacity) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.drive = drive;
        this.engineCapacity = engineCapacity;
    }

    public Vehicle(String make, String model, int year, VehicleType drive, double sailSpan) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.drive = drive;
        this.sailSpan = sailSpan;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public VehicleType getDrive() {
        return drive;
    }

    public void setDrive(VehicleType drive) {
        this.drive = drive;
    }

    public double getSailSpan() throws Exception {
        if(getDrive()==VehicleType.WIND_POWERED)
        {
            return sailSpan;
        }
        throw new Exception("This vehicle is not wind-powered");
    }

    public void setSailSpan(double sailSpan) throws Exception {
        if(getDrive()==VehicleType.WIND_POWERED)
        {
            this.sailSpan = sailSpan;
        }
        else {
            throw new Exception("This vehicle is not wind-powered");
        }
    }

    public int getEngineCapacity() throws Exception {
        if(getDrive()==VehicleType.ENGINE_POWERED)
        {
            return engineCapacity;
        }
        throw new Exception("This vehicle is not engine-powered");
    }

    public void setEngineCapacity(int engineCapacity) throws Exception {
        if(getDrive()==VehicleType.ENGINE_POWERED)
        {
            this.engineCapacity = engineCapacity;
        }
        else
        {
            throw new Exception("This vehicle is not engine-powered");
        }
    }

    public void startEngine() throws Exception {
        if(getDrive()==VehicleType.ENGINE_POWERED)
        {
            System.out.println("Engine started!");
        }
        else
        {
            throw new Exception("This vehicle is not engine-powered");
        }
    }
    public void setSail() throws Exception {
        if(getDrive()==VehicleType.WIND_POWERED)
        {
            System.out.println("Sail set!");
        }
        else
        {
            throw new Exception("This vehicle is not wind-powered");
        }
    }

    @Override
    public String toString() {
        return  "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", drive=" + drive +
                (getDrive()==VehicleType.WIND_POWERED ? ", sailSpan=" + sailSpan :
                ", engineCapacity=" + engineCapacity);
    }
}
