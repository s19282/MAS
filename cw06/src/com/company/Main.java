package com.company;

public class Main {

    public static void main(String[] args)
    {
//        abstract class
        Shape s1 = new Circle("1205FF",10,20);
        Shape s2 = new Rectangle("0611A1",44,40,20);

        System.out.println(s1.getArea());
        System.out.println(s2.getArea());

        System.out.println("=====================");
//
    }
}
