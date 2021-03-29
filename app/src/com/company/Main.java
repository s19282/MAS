package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main
{
    public static void main(String[] args)
    {
        z1(8,12);
        z2();
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

}
