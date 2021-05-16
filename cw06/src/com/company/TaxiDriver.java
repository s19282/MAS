package com.company;

public class TaxiDriver
{
    private int taxiLicenceNumber;
    private int taximeterNumber;

    public TaxiDriver(int taxiLicenceNumber, int taximeterNumber) {
        this.taxiLicenceNumber = taxiLicenceNumber;
        this.taximeterNumber = taximeterNumber;
    }

    public int getTaxiLicenceNumber() {
        return taxiLicenceNumber;
    }

    public void setTaxiLicenceNumber(int taxiLicenceNumber) {
        this.taxiLicenceNumber = taxiLicenceNumber;
    }

    public int getTaximeterNumber() {
        return taximeterNumber;
    }

    public void setTaximeterNumber(int taximeterNumber) {
        this.taximeterNumber = taximeterNumber;
    }
}
