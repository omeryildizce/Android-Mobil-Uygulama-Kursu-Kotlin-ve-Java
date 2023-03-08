package com.omeryildizce.oopproject;

public class Dog extends Animal{
    public void test(){
        super.sing();
    }

    public void sing(){
        System.out.println("Dog class");
    }
}
