package com.company;

import java.util.ArrayList;

public interface iTrainPassengers {
    void addPassenger(Passenger passenger);
    void removePassenger(Passenger passenger);
    ArrayList<Passenger> getPassengers();
}
