package com.omeryildizce.java;

public class Variables {
    public static void main(String[] args) {

        // Variables
        int age = 24;
        System.out.println(age++);
        System.out.println(age / 5);
        System.out.println(++age);

        // Double - Float
        double z = 5.0;
        double a = 11.0;

        System.out.println(a/z);
        float f = 10f;

        double pi = 3.14;
        double mathPi = Math.PI;
        int radius = 5;
        System.out.println(2* pi * radius);
        System.out.println(2* mathPi * radius);

        String name = "James";
        String surname = "Hetfield";
        String fullname = name + " " + surname;
        String fullname1 = String.format("%s %s", name, surname);

        System.out.println(fullname1);

        // final
        int myInteger = 5;
        myInteger = 4;
        System.out.printf("my integer -> %d%n", myInteger);
        final int myFinalIteger = 5;
        // myFianlIteger = 8;
        System.out.printf("my final integer -> %d%n", myFinalIteger);

    }
}

