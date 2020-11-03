package com.company;

import java.util.ArrayList;
import java.util.Objects;

public class Conductor extends Person implements iConductor {
    /*
    Переменные forgetfulness и slyness принимают значения от 0..1.
    Они определяют соответствующие характеристики проводника: забывчивость и хитрость.
    Для получения истории, в точности соответствующей заданию необходимо задать проводнику значения
    forgetfulness = slyness = 1.0; для альтернативных развитий истории - значения принадлежащие [0..1].
    В случае несоответствия переменных их области определения их значения будут скорректированы
    (округлены к ближайшему числу отрезка)
    */
    private final double forgetfulness;
    private final double slyness;

    public Conductor() {
        super("Conductor");
        super.setType(PersonTypes.conductor);
        this.slyness = 1.0;
        this.forgetfulness = 1.0;
    }

    public Conductor(String s, double slyness, double forgetfulness) {
        super(s);
        super.setType(PersonTypes.conductor);
        this.slyness = checkStat(slyness);
        this.forgetfulness = checkStat(forgetfulness);
    }

    public void checkPassengersOut(Train train) {
        Station station = train.getStation();
        Station prevStation = train.getPrevStation();
        ArrayList<Passenger> passengers= train.getPassengers();
        ArrayList<Passenger> out = new ArrayList<>();
        for(Passenger passenger: passengers) {
            if(passenger.getDestination().equals(station) && Math.random() > forgetfulness || passenger.getDestination().equals(prevStation)){
                if(passenger.isAsleep())
                    shake(passenger);
                remindToLeave(passenger);
                out.add(passenger);
            }
        }
        for(Passenger passenger: out) {
            passenger.leave(train);
        }
    }

    public void doubleCheck(Train train) {
        Station station = train.getStation();
        ArrayList<Passenger> passengers= train.getPassengers();
        int counter = 0;
        System.out.print(this + " checks if he forgot to tell anybody to leave the train.\n");
        for(Passenger passenger: passengers) {
            if(passenger.getDestination().equals(station)){
                System.out.print("Oh no! " + this + " forgot to tell " + passenger + " to leave.\n");
                counter++;
                if(slyness < Math.random()) {
                    if(passenger.isAsleep())
                        shake(passenger);
                    System.out.print(this + " tells " + passenger + " that he has skipped his station.\n");
                    say("I'm really sorry...");
                }
                else
                    System.out.print(this + " decides to wait till the next station and not to say " + passenger
                            + " that he skipped his station, because " + this +" wants to avoid explanations.\n");
            }
        }
        if(counter == 0)
            System.out.print(this + " didn't forget anyone.\n");
    }

    private void remindToLeave(Passenger passenger) {
        say(passenger + ", you have to leave now!");
    }

    private void shake(Passenger passenger) {
        if(passenger.isAsleep()) {
            System.out.print(this + " wakes " + passenger + " up.\n");
            passenger.setCondition(PassengerCondition.awake);
        }
    }

    private double checkStat(double stat) {
        if(stat > 1)
            stat = 1;
        if(stat < 0)
            stat = 0;
        return stat;
    }

    public double getSlyness() {
        return slyness;
    }

    public double getForgetfulness() {
        return forgetfulness;
    }

    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof Conductor)) return false;
        Conductor conductor = (Conductor) that;
        return Double.compare(conductor.slyness, slyness) == 0 && Double.compare(conductor.forgetfulness, forgetfulness) == 0 && Objects.equals(getName(), conductor.getName()) && Objects.equals(getType(), conductor.getType());
    }

    public String toString() {
        return getName();
    }

    public int hashCode() {
        return Objects.hash(getName(), getType(), forgetfulness, slyness);
    }
}
