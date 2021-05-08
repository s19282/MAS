package com.company;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main
{
    public static void main(String[] args)
    {
//	        "standard" association
//        Visit v1 = new Visit(LocalDate.now(),LocalDate.now().plusDays(3),600D);
//        Visit v2 = new Visit(LocalDate.now(),LocalDate.now().plusDays(19),3000D);
//
//        Repair r1 = new Repair(LocalTime.of(3,0),450D);
//        Repair r2 = new Repair(LocalTime.of(2,3),560D);
//        Repair r3 = new Repair(LocalTime.of(6,6),720D);
//
//        v1.addRepair(r1);
//        v1.addRepair(r2);
//        System.out.println(v1);
//        System.out.println("-----------");
//        System.out.println(r1.showRepair());
//        System.out.println("-----------");
//
//        r3.setVisit(v2);
//        System.out.println(r3.showRepair());
//        System.out.println("-----------");
//        System.out.println(v2);
//
//        System.out.println("===========");
////      association class
//
//        Vehicle vehicle1 = new Vehicle("Lexus", "Is", "ABC 1234");
//        Vehicle vehicle2 = new Vehicle("Subaru", "Impreza", "CBA 1234");
//
//        Client client1 = new Client("John", "Adams", 1234567);
//
//        Rent rent1 = new Rent(LocalDate.now().plusDays(1),LocalDate.now().plusDays(5), 50D,vehicle1,client1);
//        Rent rent2 = new Rent(LocalDate.now().plusDays(1),LocalDate.now().plusDays(5), 30D,vehicle1,client1);
//        Rent rent3 = new Rent(LocalDate.now().plusDays(1),LocalDate.now().plusDays(8), 70D,vehicle2,client1);
//
//        System.out.println(vehicle1);
//        System.out.println(vehicle2);
//        System.out.println("-----------");
//
//        System.out.println(client1);
//        System.out.println("-----------");
//
//        System.out.println(rent1);
//        System.out.println("===========");
//        todo check what happened after update
//        qualified association
        Employee employee1 = new Employee("Helen","Hurt",1);
        Employee employee2 = new Employee("Leo", "Graham", 33);

        Order order1 = new Order("Product1, Product2");
        Order order2 = new Order("Product3, Product5");
        employee1.addOrderQualified(order1);

        System.out.println(employee1);
        System.out.println(order1);

        System.out.println("-----------");
        order2.addEmployee(employee2);
        System.out.println(employee2);
        System.out.println(order2);


    }
}
