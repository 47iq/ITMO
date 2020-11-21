package com.company;

import java.util.Objects;

public class SomeTimeCounter implements TimeCounter{
    private int currentHour;
    SomeTimeCounter(int currentHour) {
        this.currentHour = currentHour;
    }

    public int getHour() {
        return this.currentHour;
    }

    public void addHours(int number) {
        currentHour = (currentHour + number) % 24;
    }

    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof SomeTimeCounter)) return false;
        SomeTimeCounter counter = (SomeTimeCounter) that;
        return Objects.equals(currentHour, counter.currentHour);
    }

    public String toString() {
        String out = "";
        if(currentHour < 12) {
           out += currentHour + " am.";
        }
        else {
            out += (currentHour - 12) + " pm.";
        }
        return out;
    }

    public int hashCode() {
        return Objects.hash(currentHour);
    }
}
