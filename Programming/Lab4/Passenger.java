package com.company;

import java.util.List;

public interface Passenger extends Person {
    PassengerCondition getCondition();
    Station getDestination();
    void leave(Train train);
    void sleep();
    boolean isAsleep();
    void setCondition(PassengerCondition condition);
    double getDepthOfSleep();
    void commentOnLeavingTrain(Station station);
    void fallAsleepTime(TimeCounter counter);
    List<Thing> getBaggage();
    Actions getActions();
}
