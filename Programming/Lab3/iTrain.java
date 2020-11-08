package com.company;

import java.util.ArrayList;

public interface iTrain {
    void start();
    void lastStart();
    void noCheckStart();
    void stopAt(Station station);
    Station getStation();
    Station getPrevStation();
}
