package com.company;

import java.util.ArrayList;

public interface HavingPassengers {
    void addPassenger(Passenger passenger);
    void removePassenger(Passenger passenger);
    ArrayList<Passenger> getPassengers();
}
