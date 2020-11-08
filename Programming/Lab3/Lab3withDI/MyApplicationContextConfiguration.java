package com.company;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;

@Configuration
public class MyApplicationContextConfiguration {
    @Bean
    public iConductor conductor(){
        return new Conductor();
    }

    @Bean
    public iTrain train(){
        Train train = new Train(conductor());
        train.addPassenger(passenger());
        return train;
    }

    @Bean
    public iRoute route(){
        return new Route(train(), stations());
    }

    @Bean
    public ArrayList<iStation> stations(){
        ArrayList<iStation> stations = new  ArrayList<>();
        stations.add(station1());
        stations.add(station2());
        return stations;
    }

    @Bean
    public iStation station1() {
        return new Station("Scuperfield's destination station");
    }

    @Bean
    public iStation station2() {
        return new Station("Panopticon");
    }

    @Bean
    public iPassenger passenger(){
        return new Passenger("Scuperfield", PassengerCondition.ASLEEP, station1());
    }
}