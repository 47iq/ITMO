package com.company;

import java.util.ArrayList;
import java.util.Objects;

public class SomeTrain implements Train {
    private Station station;
    private Station prevStation;
    private TrainCondition condition;
    private final Conductor conductor;
    private final TimeCounter timeCounter;
    private final ArrayList<Passenger> passengers = new ArrayList<>();

    public SomeTrain(Station station, Conductor conductor, TimeCounter timeCounter) {
        this.station = station;
        condition = TrainCondition.STAYING;
        this.conductor = conductor;
        System.out.print(this.toString());
        this.timeCounter = timeCounter;
    }

    public void start() {
        noCheckStart();
        conductor.doubleCheck(this);
        condition = TrainCondition.MOVING;
    }

    public void lastStart() {
        System.out.print("Train starts moving and goes away from " + station + ".\n");
        condition = TrainCondition.MOVING;
        System.out.print("The train disappears in the distance...\n");
    }

    public void noCheckStart() {
        System.out.print("Train starts moving and goes away from " + station + ".\n");
        condition = TrainCondition.MOVING;
    }

    public TimeCounter getTimeCounter() {
        return timeCounter;
    }

    public void stopAt(Station station) {
        condition = TrainCondition.STAYING;
        timeCounter.addHours(this.station.getStageTime());
        prevStation = this.station;
        this.station = station;
        System.out.print("Train stops at " + this.station + " at " + timeCounter + "\n");
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
    }

    public void removePassenger(Passenger passenger) {
        passengers.remove(passenger);
        String beginning = "Current station is";
        if(passenger.getDestination().equals(station))
            System.out.print( beginning + " " + passenger + "'s destination station.\n");
        else
            System.out.print( beginning +"n't " + passenger + "'s destination station\n");
        passenger.commentOnLeavingTrain(station);
    }

    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof SomeTrain)) return false;
        SomeTrain train = (SomeTrain) that;
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
