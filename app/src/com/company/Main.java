package com.company;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main
{
    public static void main(String[] args)
    {
        z1(8,12);
        z2();
        z3();
        z4();
        z5();
        z6();
    }

    static void z1(int a, int b)
    {
        int[] tab = new int[10];
        for(int i=0; i<tab.length; i++)
        {
            tab[i]=(int)(Math.random()*(b-a+1)+a);
            System.out.println(tab[i]);
        }
        System.out.println("Max generated value: "+Arrays.stream(tab).max().getAsInt());
        System.out.println("Min generated value: "+Arrays.stream(tab).min().getAsInt());
    }

    static void z2()
    {
        int[] array = new int[5];
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        Map<Integer,String> map = new HashMap<>();

        array[0] = 1;
        array[1] = 2;
        array[2] = 3;
        array[3] = 4;
        array[4] = 5;

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);

        map.put(1,"1");
        map.put(2,"2");
        map.put(3,"3");
        map.put(4,"4");
        map.put(5,"5");

        for (int i : array)
        {
            array[i-1] = i>3 ? -1 : i;
        }

        list = list.stream().filter(i->i<3).collect(Collectors.toList());
        list.removeAll(list.stream().filter(i->i>2).collect(Collectors.toList()));

        set.removeAll(set.stream().filter(i->i>3).collect(Collectors.toSet()));

        map.remove(1);
        map.entrySet().removeAll(map.entrySet().stream().filter(v->v.getKey()>3).collect(Collectors.toList()));

        System.out.println(Arrays.toString(array));
        System.out.println(list);
        System.out.println(set);
        System.out.println(map);
    }

    static void z3()
    {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Dog());
        animals.add(new Cat());
        for (Animal a : animals)
        {
            a.move();
        }
    }

    static void z4()
    {
        Engine engine = new Engine();

        engine.makeSound(new Rotary());
        engine.makeSound(new Piston());
        engine.makeSound(new Electric());
    }

    static void z5()
    {
        List<Product> products = new ArrayList<>();

        for(int i=0; i<10; i++)
        {
            products.add(new Product(Math.random()+"",Math.random()*10));
        }
        System.out.println(products);

        List<Product> filteredProducts = products.stream().filter(i-> i.price>5).collect(Collectors.toList());
        System.out.println(filteredProducts);


        double sum = products.stream().mapToDouble(i->i.price).sum();
        System.out.println("Sum: "+sum);

        System.out.println("sum>50 "+(sum>50));


    }

    static void z6()
    {
        List<Student> students = new ArrayList<>();
        students.add(new Student("fName1","lName1",1));
        students.add(new Student("fName2","lName2",2));
        students.add(new Student("fName3","lName3",3));

        File file = new File("students.csv");
        try(PrintWriter pw = new PrintWriter(file))
        {
            students.forEach(i->pw.println(i+","));
            System.out.println("Written to file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try (Stream<String> lines = Files.lines(Paths.get("students.csv"), Charset.defaultCharset()))
        {
            lines.forEachOrdered(System.out::println);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
