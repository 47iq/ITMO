package com.company;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyApplicationContextConfiguration {
    @Bean
    public Conductor conductor(){
        return new SomeConductor("Conductor", 1.0);
    }

    @Bean
    public Train train(){
        Train train = new SomeTrain(station0(), conductor(), timeCounter());
        train.addPassenger(passenger());
        train.addPassenger(passenger2());
        return train;
    }

    @Bean
    public TimeCounter timeCounter(){
        return new SomeTimeCounter(3);
    }

    @Bean
    public Route route(){
        return new SomeRoute(train(), stations());
    }

    @Bean
    public List<Station> stations(){
        List<Station> stations = new  ArrayList<>();
        stations.add(station1());
        stations.add(station2());
        stations.add(station3());
        return stations;
    }

    @Bean
    public Thing thing1() {
        return new SomeThing("newspaper");
    }

    @Bean
    public Thing thing2() {
        return new SomeThing("top hat with some stuff");
    }

    @Bean List<Thing> baggage(){
        List<Thing> baggage = new ArrayList<>();
        baggage.add(thing1());
        baggage.add(thing2());
        return baggage;
    }

    @Bean
    public Station station0() {
        return new SomeStation("some station", 2);
    }
    @Bean
    public Station station1() {
        return new SomeStation("Brehenville", 1);
    }

    @Bean
    public Station station2() {
        return new SomeStation("Panopticon", 1);
    }

    @Bean
    public Station station3() {
        return new SomeStation("San-Komarik", 10);
    }

    @Bean
    public Passenger passenger(){
        return new SomePassenger("Scuperfield", PassengerCondition.REGULAR_AWAKE, station1(), 10, baggage(), actions());
    }

    @Bean Actions actions(){
        return new ScuperfieldActions();
    }

    @Bean Actions actions2(){
        return new NeznaykaAndKozlikActions();
    }

    @Bean
    public Passenger passenger2(){
        return new SomeGroupOfPassengers("Neznayka and Kozlik", PassengerCondition.SATISFIED, station3(), 5, actions2());
    }
}