package com.company;

import java.time.LocalDate;

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
//      subset
        Sensor sensor = new Sensor("wind","dc4rg00");
        Device device = new Device("DJI","Air2s",1000D);

        sensor.setDevice(device);
        try {
            sensor.setDeviceSubset(device);
//            device.removeSensor(sensor);
//            sensor.setDeviceSubset(device);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sensor);
        System.out.println(device);

        System.out.println("================");
//      ordered
        Employee e1 = new Employee("Fred","Rodgers",43);
        Employee e2 = new Employee("Naomi","Miller",2);
        Order o1 = new Order("Pizza");

        e1.addOrder(o1);
        System.out.println(e1);
        System.out.println(o1);
        System.out.println("----------------");
        o1.addEmployee(e2);
        System.out.println(e1);
        System.out.println(o1);

        System.out.println("================");
//      bag
        Car car = new Car("Mazda","3","CBA 01234");
        Customer customer = new Customer("Franklin","White",547123764);
        Rent rent = new Rent(LocalDate.now().plusDays(4),LocalDate.now().plusDays(10),40D,car,customer);
        Rent rent2 = new Rent(LocalDate.now().plusDays(11),LocalDate.now().plusDays(21),40D,car,customer);

        System.out.println(car);
        System.out.println(rent);
        System.out.println(rent2);
        System.out.println(customer);

        System.out.println("================");
//      xor
        Sponsor sponsor = new Sponsor("Charity",10000D);
        Person person = new Person("Leo","Seward",123654789);
        Company company = new Company("IBM",1234567765,443434333);

        try
        {
            company.sponsor(sponsor);
//            person.sponsor(sponsor);
            System.out.println(company);
            System.out.println(sponsor);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("================");
//      own
        try {
            CarPart carPart = new CarPart("Driveshaft",1000D,"BPG-23432");
            System.out.println(carPart);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("================");

    }

}
