package com.omeryildizce.oopdetails;

public class Kopek extends  Hayvan implements Egitim{
    public Kopek() {
    }

    public Kopek(String isim, String gozRengi, String tuyRengi) {
        super(isim, gozRengi, tuyRengi);
    }

    @Override
    public String toString() {
        return "Kopek{" +
                "isim='" + isim + '\'' +
                ", gozRengi='" + gozRengi + '\'' +
                ", tuyRengi='" + tuyRengi + '\'' +
                '}';
    }

    @Override
    public boolean egitimAl() {
        System.out.println("köpekler eğitim alır");
        return true;
    }
}
