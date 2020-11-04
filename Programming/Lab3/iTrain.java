package com.company;

import java.util.ArrayList;

public interface iTrain {
    void lastStart();
    void noCheckStart();
    void start();
    void stopAt(Station station);
    Station getStation();
    Station getPrevStation();
    void addPassenger(Passenger passenger);
    void removePassenger(Passenger passenger);
    ArrayList<Passenger> getPassengers();
}
