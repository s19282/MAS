package com.company;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main
{
    public static void main(String[] args)
    {
//	        "standard" association
        Visit v1 = new Visit(LocalDate.now(),LocalDate.now().plusDays(3),600D);
        Visit v2 = new Visit(LocalDate.now(),LocalDate.now().plusDays(19),3000D);

        Repair r1 = new Repair(LocalTime.of(3,0),450D);
        Repair r2 = new Repair(LocalTime.of(2,3),560D);
        Repair r3 = new Repair(LocalTime.of(6,6),720D);
        Repair r4 = new Repair(LocalTime.of(7,3),90D);

        v1.addRepair(r1);
        v1.addRepair(r2);
        System.out.println(v1);
        System.out.println("-----------");
        System.out.println(r1.showRepair());
        System.out.println("-----------");

        r3.setVisit(v2);
        System.out.println(r3.showRepair());
        System.out.println("-----------");
        System.out.println(v2);
    }
}
