package com.company;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;

@Configuration
public class MyApplicationContextConfiguration {
    @Bean
    public Conductor conductor(){
        return new Conductor();
    }

    @Bean
    public Train train(){
        Train train = new Train(conductor());
        train.addPassenger(passenger());
        return train;
    }

    @Bean
    public Route route(){
        return new Route(train(), stations());
    }

    @Bean
    public ArrayList<Station> stations(){
        ArrayList<Station> stations = new  ArrayList<>();
        stations.add(station1());
        stations.add(station2());
        return stations;
    }

    @Bean
    public Station station1() {
        return new Station("Scuperfield's destination station");
    }

    @Bean
    public Station station2() {
        return new Station("Panopticon");
    }

    @Bean
    public Passenger passenger(){
        return new Passenger("Scuperfield", PassengerCondition.ASLEEP, station1());
    }
}