package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
	    z1();
	    z2();
	    z3();
    }

    static void z1()
    {
        MyMap<Integer,String> map = new MyMap<>(45,"TB");
        System.out.println(map);

        MyMap<Double,Float> map2 = new MyMap<>(1D,1F);
        System.out.println(map2);
    }

    static void z2()
    {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for(int i=0; i<100; i++)
            list.add(random.nextInt(100));
        list.stream().filter(i->i%2==1).forEach(System.out::println);
    }
    static void z3()
    {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for(int i=0; i<100; i++)
            list.add(random.nextInt(100));
        System.out.println("Sum: "+list.stream().mapToInt(Integer::intValue).sum());

    }
}
