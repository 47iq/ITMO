package com.company;

import java.util.Objects;

public abstract class SomeThingPattern implements Thing{
    private final String name;
    private final double price;

    public SomeThingPattern(String name, double price){
        this.name = name;
        this.price = price;
        if(price <= 0) throw new IllegalArgumentException();
    }

    public SomeThingPattern(String name){
        this.name = name;
        price = 300;
    }

    public double getPrice() {
        return this.price;
    }
    public String getName() {
        return name;
    }

    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof SomeThingPattern)) return false;
        SomeThingPattern someThing = (SomeThingPattern) that;
        return Objects.equals(name, someThing.name);
    }

    public String toString() {
        return this.name;
    }

    public int hashCode() {
        return Objects.hash(name);
    }
}
