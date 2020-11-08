package com.company;

import java.util.ArrayList;

public interface iTrain {
    void start();
    void lastStart();
    void noCheckStart();
    void stopAt(iStation station);
    iStation getStation();
    iStation getPrevStation();
    void addPassenger(iPassenger passenger);
    void removePassenger(iPassenger passenger);
    ArrayList<iPassenger> getPassengers();
}
