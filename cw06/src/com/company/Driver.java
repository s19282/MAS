package com.company;

import java.time.LocalDate;
import java.util.EnumSet;

enum DriverType { DRIVER,LORRY_DRIVER,TAXI_DRIVER }

public class Driver
{
    private String firstName;
    private String lastName;
    private int drivingLicenceNumber;
    private LocalDate dateOfObtain;

    private int commercialDrivingLicenceNumber;
    private int taxiLicenceNumber;
    private int taximeterNumber;

    private final EnumSet<DriverType> driverTypes = EnumSet.of(DriverType.DRIVER);
//    TODO: add method
    public Driver(String firstName, String lastName, int drivingLicenceNumber, LocalDate dateOfObtain)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.drivingLicenceNumber = drivingLicenceNumber;
        this.dateOfObtain = dateOfObtain;
    }

    public Driver(String firstName, String lastName, int drivingLicenceNumber, LocalDate dateOfObtain, int commercialDrivingLicenceNumber, int taxiLicenceNumber, int taximeterNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.drivingLicenceNumber = drivingLicenceNumber;
        this.dateOfObtain = dateOfObtain;
        this.commercialDrivingLicenceNumber = commercialDrivingLicenceNumber;
        this.taxiLicenceNumber = taxiLicenceNumber;
        this.taximeterNumber = taximeterNumber;
        driverTypes.add(DriverType.TAXI_DRIVER);
        driverTypes.add(DriverType.LORRY_DRIVER);
    }

    public Driver(String firstName, String lastName, int drivingLicenceNumber, LocalDate dateOfObtain, int commercialDrivingLicenceNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.drivingLicenceNumber = drivingLicenceNumber;
        this.dateOfObtain = dateOfObtain;
        this.commercialDrivingLicenceNumber = commercialDrivingLicenceNumber;
        driverTypes.add(DriverType.LORRY_DRIVER);
    }

    public Driver(String firstName, String lastName, int drivingLicenceNumber, LocalDate dateOfObtain, int taxiLicenceNumber, int taximeterNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.drivingLicenceNumber = drivingLicenceNumber;
        this.dateOfObtain = dateOfObtain;
        this.taxiLicenceNumber = taxiLicenceNumber;
        this.taximeterNumber = taximeterNumber;
        driverTypes.add(DriverType.TAXI_DRIVER);
    }

    public int getCommercialDrivingLicenceNumber() throws Exception {
        if(driverTypes.contains(DriverType.LORRY_DRIVER))
        {
            return commercialDrivingLicenceNumber;
        }
        throw new Exception("This driver is not an Lorry Driver");
    }

    public void setCommercialDrivingLicenceNumber(int commercialDrivingLicenceNumber) throws Exception {
        if(driverTypes.contains(DriverType.LORRY_DRIVER))
        {
            this.commercialDrivingLicenceNumber = commercialDrivingLicenceNumber;
        }
        throw new Exception("This driver is not an Lorry Driver");
    }

    public int getTaxiLicenceNumber() throws Exception {
        if(driverTypes.contains(DriverType.TAXI_DRIVER))
        {
            return taxiLicenceNumber;
        }
        throw new Exception("This driver is not an Taxi Driver");
    }

    public void setTaxiLicenceNumber(int taxiLicenceNumber) throws Exception {
        if(driverTypes.contains(DriverType.TAXI_DRIVER))
        {
            this.taxiLicenceNumber = taxiLicenceNumber;
        }
        throw new Exception("This driver is not an Taxi Driver");
    }

    public int getTaximeterNumber() throws Exception {
        if(driverTypes.contains(DriverType.TAXI_DRIVER))
        {
            return taximeterNumber;
        }
        throw new Exception("This driver is not an Taxi Driver");
    }

    public void setTaximeterNumber(int taximeterNumber) throws Exception {
        if(driverTypes.contains(DriverType.TAXI_DRIVER))
        {
            this.taximeterNumber = taximeterNumber;
        }
        throw new Exception("This driver is not an Taxi Driver");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDrivingLicenceNumber() {
        return drivingLicenceNumber;
    }

    public void setDrivingLicenceNumber(int drivingLicenceNumber) {
        this.drivingLicenceNumber = drivingLicenceNumber;
    }

    public LocalDate getDateOfObtain() {
        return dateOfObtain;
    }

    public void setDateOfObtain(LocalDate dateOfObtain) {
        this.dateOfObtain = dateOfObtain;
    }

    @Override
    public String toString() {
        if( driverTypes.contains(DriverType.DRIVER) &&
            driverTypes.contains(DriverType.LORRY_DRIVER) &&
            driverTypes.contains(DriverType.TAXI_DRIVER))
        {
            return "Lorry & Taxi Driver{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", drivingLicenceNumber=" + drivingLicenceNumber +
                    ", dateOfObtain=" + dateOfObtain +
                    ", commercialDrivingLicenceNumber=" + commercialDrivingLicenceNumber +
                    ", taxiLicenceNumber=" + taxiLicenceNumber +
                    ", taximeterNumber=" + taximeterNumber +
                    '}';
        }
        else if( driverTypes.contains(DriverType.DRIVER) &&
                driverTypes.contains(DriverType.LORRY_DRIVER))
        {
            return "Lorry Driver{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", drivingLicenceNumber=" + drivingLicenceNumber +
                    ", dateOfObtain=" + dateOfObtain +
                    ", commercialDrivingLicenceNumber=" + commercialDrivingLicenceNumber +
                    '}';
        }
        else if( driverTypes.contains(DriverType.DRIVER) &&
                driverTypes.contains(DriverType.TAXI_DRIVER))
        {
            return "Taxi Driver{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", drivingLicenceNumber=" + drivingLicenceNumber +
                    ", dateOfObtain=" + dateOfObtain +
                    ", taxiLicenceNumber=" + taxiLicenceNumber +
                    ", taximeterNumber=" + taximeterNumber +
                    '}';
        }
        else
        {
            return "Driver{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", drivingLicenceNumber=" + drivingLicenceNumber +
                    ", dateOfObtain=" + dateOfObtain +
                    '}';
        }
    }
}
