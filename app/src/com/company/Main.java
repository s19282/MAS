package com.company;

import java.util.Arrays;

public class Main
{
    public static void main(String[] args)
    {
        z1(8,12);
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

}
