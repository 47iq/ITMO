package com.company;

import java.util.ArrayList;

public class Main {
    /*
    Скуперфильд, однако, продолжал спать.
    Проводник забыл его разбудить и вспомнил об этом, лишь когда поезд уже отошел от станции.
    Чтоб избежать неприятных объяснений, он решил пока не будить Скуперфильда,
    а принялся тормошить его, как только поезд остановился на следующей станции,
    которая имела какое-то странное название -- "Паноптикум".
    */
    public static void main(String[] args) {
        public static ArrayList<String> out = new ArrayList<>();

        private static void print() {
            for(String string: out) {
                System.out.print(string);
            }
        }
        //Conductor conductor = new Conductor("Conductor", 1, 1);
        Conductor conductor = new Conductor();
        Train train = new Train(conductor);
        Station station1 = new Station("Scuperfield's destination station");
        Station station2 = new Station("Panopticon");
        Passenger passenger = new Passenger("Scuperfield", PassengerCondition.ASLEEP, station1);
        train.addPassenger(passenger);
        ArrayList<Station> stations = new ArrayList<>();
        stations.add(station1);
        stations.add(station2);
        Route route = new Route(train, stations);
        route.runFullRoute();
        print();
    }
}
