package com.company;

import java.io.*;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args)
    {
        Visit visit1 = new Visit(LocalDate.now(), LocalDate.now().plusDays(1),
                                LocalDate.now().plusDays(2), 50D, 60D);
        Visit visit2 = new Visit(LocalDate.now(), LocalDate.now().plusDays(2),45D);
        System.out.println(visit1);
        System.out.println(visit2);
        System.out.println(visit2.getCost());

        Visit.showExtent();

        try
        {
            FileOutputStream fos = new FileOutputStream("extent.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Visit.writeExtent(oos);


            FileInputStream fis = new FileInputStream("extent.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Visit.readExtent(ois);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
