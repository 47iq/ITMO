package com.company;

public interface iPassenger extends iPerson {
    PassengerCondition getCondition();
    iStation getDestination();
    void leave(iTrain train);
    void sleep();
    boolean isAsleep();
    void setCondition(PassengerCondition condition);
}
