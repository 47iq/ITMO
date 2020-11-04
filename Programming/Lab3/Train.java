package com.company;

import java.util.ArrayList;
import java.util.Objects;

public class Train implements iTrain{
    private Station station;
    private Station prevStation;
    private TrainCondition condition;
    private final Conductor conductor;
    private final ArrayList<Passenger> passengers = new ArrayList<>();

    public Train(Station station, Conductor conductor) {
        this.station = station;
        condition = TrainCondition.STAYING;
        this.conductor = conductor;
        System.out.print(this);
    }

    public Train(Conductor conductor) {
        station = new Station("some station");
        condition = TrainCondition.STAYING;
        this.conductor = conductor;
        System.out.print(this);
    }

    public Train() {
        conductor = new Conductor();
        station = new Station("some station");
        condition = TrainCondition.STAYING;
        System.out.print(this);
    }

    public void start() {
        System.out.print("Train starts moving and goes away from " + station + ".\n");
        for(Passenger passenger : passengers)
            if(passenger.isAsleep())
                passenger.sleep();
        conductor.doubleCheck(this);
        condition = TrainCondition.MOVING;
    }

    public void lastStart() {
        System.out.print("Train starts moving and goes away from " + station + ".\n");
        condition = TrainCondition.MOVING;
        System.out.print("The train disappears in the distance...\n");
    }

    public void stopAt(Station station) {
        condition = TrainCondition.STAYING;
        prevStation = this.station;
        this.station = station;
        System.out.print("Train stops at " + station + ".\n");
        conductor.checkPassengersOut(this);
    }

    public Station getStation(){
        return station;
    }


    public Station getPrevStation(){
        return prevStation;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
        System.out.print(passenger + " is on the train.\n");
    }

    public void removePassenger(Passenger passenger) {
        passengers.remove(passenger);
        String beginning = "Current station is";
        if(passenger.getDestination().equals(station))
            System.out.print( beginning + " " + passenger + "'s destination station.\n"
                    + passenger + " is really happy about that. He thanks " + conductor + ".\n");
        else
            System.out.print( beginning +"n't " + passenger + "'s destination station, so " +
                    passenger+ " is very annoyed now.\n");
        System.out.print(passenger + " leaves the train at " + getStation() + ".\n");
    }

    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof Train)) return false;
        Train train = (Train) that;
        return Objects.equals(station, train.station) && Objects.equals(prevStation, train.prevStation)
                && Objects.equals(condition, train.condition) && Objects.equals(passengers, train.passengers);
    }

    public String toString() {
        final String BEGINNING = "Train is " + condition;
        final String ENDING = station + ". The train's conductor's name is " + conductor.getName() + ".\n";
        if(condition.equals(TrainCondition.MOVING))
            return BEGINNING + " from " + ENDING;
        else
            return BEGINNING + " at " + ENDING;
    }

    public int hashCode() {
        return Objects.hash(station, prevStation, condition, passengers);
    }
}
