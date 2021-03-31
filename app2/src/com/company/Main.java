package com.company;

public class Main {

    public static void main(String[] args) {
	    z1();
    }

    static void z1()
    {
        MyMap<Integer,String> map = new MyMap<>(45,"TB");
        System.out.println(map);

        MyMap<Double,Float> map2 = new MyMap<>(1D,1F);
        System.out.println(map2);
    }
}
