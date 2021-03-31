package com.company;

public class Engine
{
    public void makeSound(Rotary engineType)
    {
        engineType.showType();
        System.out.println("Rotary engine sound");
    }
    public void makeSound(Piston engineType)
    {
        engineType.showType();
        System.out.println("Piston engine sound");
    }
    public void makeSound(Electric engineType)
    {
        engineType.showType();
        System.out.println("Electric engine sound");
    }
}
