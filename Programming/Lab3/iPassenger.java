package com.company;

public interface iPassenger {
    PassengerCondition getCondition();
    Station getDestination();
    void leave(Train train);
}
