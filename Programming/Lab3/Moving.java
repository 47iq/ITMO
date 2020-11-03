package com.company;

public interface Moving {
    void start();
    void stopAt(Station station);
    Station getStation();
    Station getPrevStation();
}
