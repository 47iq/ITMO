package com.company;

import java.util.Objects;

public class Station implements iStation{
    private final String name;
    private static int ind = 0;

    public Station(String name) {
        this.name = name;
    }

    public Station() {
        this.name = "Station " + ind;
        ind++;
    }

    public String getName(){
        return name;
    }

    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof Station)) return false;
        Station station = (Station) that;
        return Objects.equals(name, station.name);
    }

    public String toString() {
        return name;
    }

    public int hashCode() {
        return Objects.hash(name);
    }
}
