package com.omeryildizce.java;

public class Loops {
    public static void main(String[] args) {

        int[] myNumbers = {2, 3, 5, 7, 11, 13, 17, 19, 23, 27, 29};
        for (int i = 0; i < myNumbers.length; i++) {
            System.out.println(myNumbers[i]);
        }

        for (int number : myNumbers) {
            System.out.println();
        }


        int j = 0;
        while (j < 10000000) {
            System.out.println(j);
            j++;
        }
    }
}
