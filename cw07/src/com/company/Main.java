package com.company;

public class Main {

    public static void main(String[] args)
    {
//      attribute constraint
        Client c = new Client("John");
        System.out.println(c);
        try
        {
            c.setDiscount(.3f);
            System.out.println(c);
//            c.setDiscount(.5f);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("================");
//      unique

        try
        {
            Vehicle v = new Vehicle("Mazda","3","JMZBK12Z24111111");
            System.out.println(v);
//            Vehicle v2 = new Vehicle("Mazda","3","JMZBK12Z24111111");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("================");

    }
}
