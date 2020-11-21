package com.company;

import java.util.List;
import java.util.Objects;

public class SomeRoute implements Route {
    private final List<Station> stations;
    private final Train train;

    public SomeRoute(Train train, List<Station> stations) {
        this.stations = stations;
        this.train = train;
    }

    private void timeToSleepCheck() {
        List<Passenger> passengers = train.getPassengers();
        TimeCounter timeCounter = train.getTimeCounter();
        if(timeCounter.getHour() < 6)
            for(Passenger passenger: passengers)
                passenger.fallAsleepTime(timeCounter);
        for(Passenger passenger : passengers)
            if(passenger.isAsleep())
                passenger.sleep();
    }

    public void runFullRoute() throws UnableToFall {
        int ind = 0;
        for(Station station: stations) {
            if(ind == 0 && !train.getPassengers().isEmpty()) {
                train.noCheckStart();
                for(Passenger passenger: train.getPassengers()) {
                    Actions actions = passenger.getActions();
                    if(actions != null)
                        actions.completeActions(passenger);
                }
                timeToSleepCheck();
                train.stopAt(station);
            }
            else if (!train.getPassengers().isEmpty()) {
                train.start();
                timeToSleepCheck();
                train.stopAt(station);
            }
            else {
                train.lastStart();
            }
            ind++;
        }
        train.lastStart();
    }

    public int hashCode(){
        return Objects.hash(stations, train);
    }

    public String toString() {
        return "The route is: " + stations + " with a " + train;
    }

    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof SomeRoute)) return false;
        SomeRoute route = (SomeRoute)that;
        return Objects.equals(route.stations, stations)
                && Objects.equals(route.train, train);
    }
}
