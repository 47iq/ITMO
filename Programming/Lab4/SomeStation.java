package com.company;

import java.util.Objects;

public class SomeStation implements Station{
    private final String name;
    private final int stageTime;

    public SomeStation(String name, int stageTime) {
        this.name = name;
        this.stageTime = stageTime;
    }

    public String getName(){
        return name;
    }

    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof SomeStation)) return false;
        SomeStation station = (SomeStation) that;
        return Objects.equals(name, station.name);
    }

    public int getStageTime() {
        return stageTime;
    }

    public String toString() {
        return name;
    }

    public int hashCode() {
        return Objects.hash(name);
    }
}
