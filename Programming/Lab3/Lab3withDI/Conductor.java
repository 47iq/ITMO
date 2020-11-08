package com.company;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

public class Conductor extends Person implements iConductor{
    /*
    Переменные forgetfulness и slyness принимают значения от 0..1.
    Они определяют соответствующие характеристики проводника: забывчивость и хитрость.
    Для получения истории, в точности соответствующей заданию необходимо задать проводнику значения
    forgetfulness = slyness = 1.0;
    */
    private final double forgetfulness;
    private final double slyness;

    public Conductor() {
        super("Conductor");
        super.setType(PersonTypes.CONDUCTOR);
        this.slyness = 1.0;
        this.forgetfulness = 1.0;
    }

    public Conductor(String s, double slyness, double forgetfulness) {
        super(s);
        super.setType(PersonTypes.CONDUCTOR);
        this.slyness = slyness;
        this.forgetfulness = forgetfulness;
    }

    public void checkPassengersOut(iTrain train) {
        iStation station = train.getStation();
        iStation prevStation = train.getPrevStation();
        ArrayList<iPassenger> passengers= train.getPassengers();
        ArrayList<iPassenger> out = new ArrayList<>();
        for(iPassenger passenger: passengers) {
            if(passenger.getDestination().equals(station) && Math.random() > forgetfulness || passenger.getDestination().equals(prevStation)){
                if(passenger.isAsleep())
                    shake(passenger);
                remindToLeave(passenger);
                out.add(passenger);
            }
        }
        for(iPassenger passenger: out) {
            passenger.leave(train);
        }
    }

    public void doubleCheck(iTrain train) {
        iStation station = train.getStation();
        ArrayList<iPassenger> passengers= train.getPassengers();
        int counter = 0;
        Main.out.add(this + " checks if he forgot to tell anybody to leave the train.\n");
        for(iPassenger passenger: passengers) {
            if(passenger.getDestination().equals(station)){
                Main.out.add("Oh no! " + this + " forgot to tell " + passenger + " to leave.\n");
                counter++;
                if(slyness < Math.random()) {
                    if(passenger.isAsleep())
                        shake(passenger);
                    Main.out.add(this + " tells " + passenger + " that he has skipped his station.\n");
                    say("I'm really sorry...");
                }
                else
                    Main.out.add(this + " decides to wait till the next station and not to say " + passenger
                            + " that he skipped his station, because " + this +" wants to avoid explanations.\n");
            }
        }
        if(counter == 0)
            Main.out.add(this + " didn't forget anyone.\n");
    }

    private void remindToLeave(iPassenger passenger) {
        say(passenger + ", you have to leave now!");
    }

    private void shake(iPassenger passenger) {
        if(passenger.isAsleep()) {
            Main.out.add(this + " wakes " + passenger + " up.\n");
            passenger.setCondition(PassengerCondition.AWAKE);
        }
    }

    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof Conductor)) return false;
        Conductor conductor = (Conductor) that;
        return Double.compare(conductor.slyness, slyness) == 0
                && Double.compare(conductor.forgetfulness, forgetfulness) == 0
                && Objects.equals(getName(), conductor.getName()) && Objects.equals(getType(), conductor.getType());
    }

    public String toString() {
        return getName();
    }

    public int hashCode() {
        return Objects.hash(getName(), getType(), forgetfulness, slyness);
    }
}
