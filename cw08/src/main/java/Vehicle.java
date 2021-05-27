enum FuelType {PETROL,DIESEL}

public class Vehicle
{
    private String make;
    private String model;
    private int engineCapacity;
    private FuelType fuelType;
    private int year;

    public Vehicle(String make, String model, int engineCapacity, FuelType fuelType, int year) {
        this.make = make;
        this.model = model;
        this.engineCapacity = engineCapacity;
        this.fuelType = fuelType;
        this.year = year;
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

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
