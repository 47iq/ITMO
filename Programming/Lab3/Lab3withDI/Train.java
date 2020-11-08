package com.company;

import java.util.ArrayList;
import java.util.Objects;

public class Train implements iTrain, iTrainPassengers{
    private Station station;
    private Station prevStation;
    private TrainCondition condition;
    private final Conductor conductor;
    private final ArrayList<Passenger> passengers = new ArrayList<>();

    public Train(Station station, Conductor conductor) {
        this.station = station;
        condition = TrainCondition.STAYING;
        this.conductor = conductor;
        Main.out.add(this.toString());
    }

    public Train(Conductor conductor) {
        station = new Station("some station");
        condition = TrainCondition.STAYING;
        this.conductor = conductor;
        Main.out.add(this.toString());
    }

    public Train() {
        conductor = new Conductor();
        station = new Station("some station");
        condition = TrainCondition.STAYING;
        Main.out.add(this.toString());
    }

    public void start() {
        noCheckStart();
        conductor.doubleCheck(this);
        condition = TrainCondition.MOVING;
    }

    public void lastStart() {
        Main.out.add("Train starts moving and goes away from " + station + ".\n");
        condition = TrainCondition.MOVING;
        Main.out.add("The train disappears in the distance...\n");
    }

    public void noCheckStart() {
        Main.out.add("Train starts moving and goes away from " + station + ".\n");
        for(Passenger passenger : passengers)
            if(passenger.isAsleep())
                passenger.sleep();
        condition = TrainCondition.MOVING;
    }

    public void stopAt(Station station) {
        condition = TrainCondition.STAYING;
        prevStation = this.station;
        this.station = station;
        Main.out.add("Train stops at " + station + ".\n");
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
        Main.out.add(passenger + " is on the train.\n");
    }

    public void removePassenger(Passenger passenger) {
        passengers.remove(passenger);
        String beginning = "Current station is";
        if(passenger.getDestination().equals(station))
            Main.out.add( beginning + " " + passenger + "'s destination station.\n"
                    + passenger + " is really happy about that. He thanks " + conductor + ".\n");
        else
            Main.out.add( beginning +"n't " + passenger + "'s destination station, so " +
                    passenger+ " is very annoyed now.\n");
        Main.out.add(passenger + " leaves the train at " + getStation() + ".\n");
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
