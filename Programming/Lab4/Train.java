package com.company;

import java.util.ArrayList;

public interface Train {
    void start();
    void lastStart();
    void noCheckStart();
    void stopAt(Station station);
    Station getStation();
    Station getPrevStation();
    void addPassenger(Passenger passenger);
    void removePassenger(Passenger passenger);
    ArrayList<Passenger> getPassengers();
    TimeCounter getTimeCounter();
}
