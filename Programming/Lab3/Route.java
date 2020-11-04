package com.company;

import java.util.ArrayList;
import java.util.Objects;

public class Route implements iRoute {
    private final ArrayList<Station> stations;
    private final Train train;

    public Route(Train train, ArrayList<Station> stations) {
        this.stations = stations;
        this.train = train;
    }

    public Route(Train train) {
        ArrayList<Station> stations = new ArrayList<>();
        stations.add(new Station());
        stations.add(new Station());
        this.stations = stations;
        this.train = train;
    }

    public Route() {
        ArrayList<Station> stations = new ArrayList<>();
        stations.add(new Station());
        stations.add(new Station());
        this.stations = stations;
        this.train = new Train();
    }

    public void runFullRoute() {
        final String DISAPPEAR = "The train disappears in the distance...\n";
        for(Station station: stations) {
            if (!train.getPassengers().isEmpty()) {
                train.start();
                train.stopAt(station);
            }
            else {
                train.lastStart();
            }
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
        if (!(that instanceof Route)) return false;
        Route route = (Route)that;
        return Objects.equals(route.stations, stations)
                && Objects.equals(route.train, train);
    }
}
