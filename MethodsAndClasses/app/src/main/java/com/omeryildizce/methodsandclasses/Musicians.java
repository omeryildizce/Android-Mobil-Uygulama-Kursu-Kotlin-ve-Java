package com.omeryildizce.methodsandclasses;

public class Musicians {
    String name;
    String instrument;
    int age;

    public Musicians(String name, String instrument, int age) {
        this.name = name;
        this.instrument = instrument;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Musicians{" +
                "Name='" + name + '\'' +
                ", Instrument='" + instrument + '\'' +
                ", Age=" + age +
                '}';
    }
}
