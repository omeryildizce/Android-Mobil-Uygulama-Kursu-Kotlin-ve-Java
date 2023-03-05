package com.omeryildizce.java;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Arrays {
    public static void main(String[] args) {
        String[] myStringArray = new String[5];
        myStringArray[0] = "Harun";
        myStringArray[1] = "Veli";

        System.out.println(myStringArray[0]);
        System.out.println(myStringArray[1]);

        int[] myIntegerArray = new int[4];
        myIntegerArray[0] = 50;
        myIntegerArray[1] = 60;
        myIntegerArray[2] = 70;
        myIntegerArray[3] = 45;
        myIntegerArray[3] = 25;

        System.out.println(myIntegerArray);
        int[] myNumberArray = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(myNumberArray[2]);

        ArrayList<String> myMusicians = new ArrayList<String>();
        myMusicians.add("James");
        myMusicians.add("Ömer");
        myMusicians.add(0, "Kadir");
        myMusicians.add(2, "Ali");
        myMusicians.addAll(2, asList(myStringArray));

        System.out.println(myMusicians.get(0));
        System.out.println(myMusicians.get(1));
        System.out.println(myMusicians.get(2));
        System.out.println(myMusicians.get(3));
        System.out.println(myMusicians.get(4));
        System.out.println(myMusicians.get(5));

        // Set
        HashSet<String> mySet = new HashSet<String>();
        mySet.add("James");
        mySet.add("james");
        mySet.add("James");
        mySet.add("games");

        System.out.println(mySet.size());

        HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
        hashMap.put(0, "Ömer");
        hashMap.put(5, "Ali");
        hashMap.put(5, "Veli");
        hashMap.put(10, "Kadir");

        System.out.println("HashMap");
        System.out.println(hashMap.size());
        System.out.println(hashMap.get(0));
        System.out.println(hashMap.get(5));
        System.out.println(hashMap.get(10));

        
    }



}