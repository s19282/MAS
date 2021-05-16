package com.company;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args)
    {
//      abstract class
        Shape s1 = new Circle("1205FF",10,20);
        Shape s2 = new Rectangle("0611A1",44,40,20);

        System.out.println(s1.getArea());
        System.out.println(s2.getArea());

        System.out.println("=====================");
//      Overlapping
        Driver driver = new Driver("Alan","Vicknair",1212, LocalDate.now().minusYears(3));
        Driver lorryDriver = new Driver("Tod", "Hone", 35334, LocalDate.now().minusYears(2),54643);
        Driver taxiDriver = new Driver("Troy","Thompson",34343,LocalDate.now().minusYears(5),33232,534545);
        Driver taxiAndLorryDriver = new Driver("Carl","Tidwell",434561,LocalDate.now().minusMonths(4),4523423,34234,65656);

        System.out.println(driver);
        System.out.println(lorryDriver);
        System.out.println(taxiDriver);
        System.out.println(taxiAndLorryDriver);
        System.out.println("=====================");
//      multi-inheritance
        Amphibian amphibian = new Amphibian("LUAZ","969",4,30,1000,6);
        System.out.println(amphibian);

        System.out.println("=====================");
//      multi-aspect inheritance
        Vehicle landEnginePoweredVehicle = new LandVehicle("toyota","hilux",2010,VehicleType.ENGINE_POWERED,3000,4);
        Vehicle landWindPoweredVehicle = new LandVehicle("bike","with sail",2020,VehicleType.WIND_POWERED,2d,3);
        Vehicle waterEnginePoweredVehicle = new WaterVehicle("kawasaki","stx-15f",2011,VehicleType.ENGINE_POWERED,500,400);
        Vehicle waterWindPoweredVehicle = new WaterVehicle("Fusion","msv",2021,VehicleType.WIND_POWERED,5D,700);

        System.out.println(landEnginePoweredVehicle);
        System.out.println(landWindPoweredVehicle);
        System.out.println(waterEnginePoweredVehicle);
        System.out.println(waterWindPoweredVehicle);

        try
        {
            landEnginePoweredVehicle.startEngine();
            landWindPoweredVehicle.startEngine();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        System.out.println("=====================");
//      dynamic inheritance
        Employee employee = new Employee("Ronald","Gardener",LocalDate.now().minusYears(30),Position.MECHANIC,"engines");
        System.out.println(employee);
//        employee.setPosition(Position.PAINTER,"transmissions");
//        employee.setPosition(Position.MECHANIC,"engines");
        employee.setPosition(Position.PAINTER,500f);
        System.out.println(employee);
    }
}
