package com.omeryildizce.oopproject;

import java.util.Arrays;

public class Mathematics {
    // Static polymorphism
    public int sum(){
        return 0;
    }

    public int sum(int x, int y){
        return x + y;
    }

    public int sum(int... args){
        int sum = 0;
        for(int x: args){
            sum+=x;
        }
        System.out.println(sum);
        return sum;
    }


}
