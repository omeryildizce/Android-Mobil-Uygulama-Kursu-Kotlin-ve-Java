package com.omeryildizce.oopdetails;

public class Kedi {
    private String isim;
    private String gozRengi;
    private String tuyRengi;

    public static String turIsmi = "felis";
    public Kedi() {
    }

    public Kedi(String isim, String gozRengi, String tuyRengi) {
        this.isim = isim;
        this.gozRengi = gozRengi;
        this.tuyRengi = tuyRengi;
    }

    public static void konusediStatic(){
        System.out.println("Benim türüm: " + turIsmi);

    }
    public void konusedi(){
        System.out.println("Benim ismim: " + this.isim + "\nBenim türüm: " + turIsmi);
    }
}
