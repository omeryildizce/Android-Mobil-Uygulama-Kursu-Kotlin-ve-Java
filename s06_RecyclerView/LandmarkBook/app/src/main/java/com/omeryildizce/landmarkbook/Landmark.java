package com.omeryildizce.landmarkbook;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Landmark implements Serializable  {
    String name;
    String country;
    int image;

    public Landmark(String name, String country, int image) {
        this.name = name;
        this.country = country;
        this.image = image;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%-20s %20s", name, country);
    }
}
