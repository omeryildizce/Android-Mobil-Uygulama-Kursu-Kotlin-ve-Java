package com.omeryildizce.java;

public class Statements {
    public static void main(String[] args) {
        int x = 5;
        System.out.println(x);
        x = x+1;
        x++;
        ++x;
        x+=1;


        int y = 4;
        boolean b = x>y;
        if (b){
            System.out.println(x);
        }else {
            System.out.println(y);
        }

        System.out.println(5<10 &&  10 < 20);
        System.out.println(5<10 &&  10 < 5);
        System.out.println(5<10 || 10 < 20);

        //      İf
        if (13 < 18){
            System.out.println(21);
        }


        // switch
        int day = 5;
        switch (day){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                System.out.println("Hafta içi");
                break;
            case 6:
            case 7:
                System.out.println("Hafta sonu");
                break;
            default:
                System.out.println("Yanlış değer");
                break;
        }

    }


}
