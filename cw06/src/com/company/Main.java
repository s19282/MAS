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

    }
}
