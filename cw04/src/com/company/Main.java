package com.company;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        Repair r1 = new Repair(LocalTime.of(2,0),500D);
        Repair r2 = new Repair(LocalTime.of(1,0),100D);
        Repair r3 = new Repair(LocalTime.of(3,0),1000D);

        List<Repair> repairs = new ArrayList<>();
        repairs.add(r1);
        repairs.add(r2);
        repairs.add(r3);

        Visit visit1 = new Visit(LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now().plusDays(2), 50D, repairs);
        Visit visit2 = new Visit(LocalDate.now().minusDays(1), LocalDate.now().plusDays(2),45D, repairs);
        Visit visit3 = new Visit(LocalDate.now().minusDays(2), LocalDate.now().plusDays(3),55D, repairs);
        Visit visit4 = new Visit(LocalDate.now().minusDays(3), LocalDate.now().plusDays(4),65D, repairs);
        Visit visit5 = new Visit(LocalDate.now().minusDays(4), LocalDate.now().plusDays(5),75D, repairs);

//        Extent
        Visit.showExtent();
        System.out.println("--------------------");
//        Extent persistence
        try
        {
            FileOutputStream fos = new FileOutputStream("extent.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Visit.writeExtent(oos);
            oos.close();
            fos.close();

            visit1.remove();
            visit2.remove();
            Visit.showExtent();
            System.out.println("--------------------");

            FileInputStream fis = new FileInputStream("extent.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Visit.readExtent(ois);
            ois.close();
            fis.close();
            Visit.showExtent();
            System.out.println("--------------------");

        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
//      Non-primitive data type
        System.out.println(visit1.getStartDate());
        visit1.setStartDate(LocalDate.now().minusWeeks(1));
        System.out.println(visit1.getStartDate());
        System.out.println("--------------------");
//      Optional property
        System.out.println(visit3.getEndDate());
        System.out.println(visit3);
        System.out.println("--------------------");
//      Repeatable attribute
        System.out.println(visit5.getRepairs());
        System.out.println("--------------------");
//      Class attribute
        System.out.println(Visit.getManHourCost());
        System.out.println("--------------------");
//      Derived attribute
        System.out.println("Cost: " + visit4.getCost());
        System.out.println("--------------------");
//      Class method
        System.out.println(Visit.getVisits(LocalDate.now().minusDays(3)));
        System.out.println("--------------------");
//      Overloaded method
        System.out.println(Visit.getVisits(LocalDate.now().minusDays(3)));
        System.out.println(Visit.getVisits(LocalDate.now().minusDays(3),LocalDate.now().minusDays(1)));
        System.out.println("--------------------");
//      Override method
        System.out.println(visit1);
        System.out.println("--------------------");
    }
}
