package com.company;

import java.util.ArrayList;
import java.util.List;

public class Car
{
    private String Make;
    private String Model;
    private String transmission;
    private String driveTrain;
    private int year;
    private List<Engine> engines = new ArrayList<>();

    public Car(String make, String model, String transmission, String driveTrain, int year) {
        Make = make;
        Model = model;
        this.transmission = transmission;
        this.driveTrain = driveTrain;
        this.year = year;
    }

    public Engine createEngine(String codeName, String type, int horsePower)
    {
        Engine engine = new Engine(codeName,type,horsePower);
        engines.add(engine);

        return engine;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        Make = make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getDriveTrain() {
        return driveTrain;
    }

    public void setDriveTrain(String driveTrain) {
        this.driveTrain = driveTrain;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
//        StringBuilder enginesString = new StringBuilder();
//        for (Engine engine : engines) enginesString.append(engine);
        return "Car{" +
                "Make='" + Make + '\'' +
                ", Model='" + Model + '\'' +
                ", transmission='" + transmission + '\'' +
                ", driveTrain='" + driveTrain + '\'' +
                ", year=" + year +
                ", engines=" + engines +
                '}';
    }

    public class Engine
    {
        private String codeName;
        private String type;
        private int horsePower;

        private Engine(String codeName, String type, int horsePower)
        {
            this.codeName = codeName;
            this.type = type;
            this.horsePower = horsePower;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getHorsePower() {
            return horsePower;
        }

        public void setHorsePower(int horsePower) {
            this.horsePower = horsePower;
        }

        public String getCodeName() {
            return codeName;
        }

        public void setCodeName(String codeName) {
            this.codeName = codeName;
        }
        public String showEngine()
        {
            return "Engine{" +
                    "codeName='" + codeName + '\'' +
                    ", type='" + type + '\'' +
                    ", horsePower=" + horsePower +
                    ", Car{" +
                    "Make='" + Make + '\'' +
                    ", Model='" + Model + '\'' +
                    ", transmission='" + transmission + '\'' +
                    ", driveTrain='" + driveTrain + '\'' +
                    ", year=" + year +
                    '}' +
                    '}';
        }
        @Override
        public String toString() {
            return "Engine{" +
                    "codeName='" + codeName + '\'' +
                    ", type='" + type + '\'' +
                    ", horsePower=" + horsePower +
                    '}';
        }
    }
}
