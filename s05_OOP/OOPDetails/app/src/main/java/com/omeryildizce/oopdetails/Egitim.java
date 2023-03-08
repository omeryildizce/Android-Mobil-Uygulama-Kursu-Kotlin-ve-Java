package com.omeryildizce.oopdetails;

public interface Egitim {
    public boolean egitimAl();

    public default boolean egitimAlBody() {
        System.out.println("eğitim almıyoruz");
        return false;
    }
}
