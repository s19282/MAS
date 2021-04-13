package com.company;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args)
    {
        Visit visit1 = new Visit(LocalDate.now(), LocalDate.now().plusDays(1),
                                LocalDate.now().plusDays(2), 50D, 60D, new ArrayList<>());
        Visit visit2 = new Visit(LocalDate.now(), LocalDate.now().plusDays(2),45D, new ArrayList<>());

        Visit.showExtent();
        System.out.println("End date: "+visit2.getEndDate());

        try
        {
            FileOutputStream fos = new FileOutputStream("extent.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Visit.writeExtent(oos);

            visit1.remove();
            visit2.remove();
            Visit.showExtent();

            FileInputStream fis = new FileInputStream("extent.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Visit.readExtent(ois);
            Visit.showExtent();

        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
