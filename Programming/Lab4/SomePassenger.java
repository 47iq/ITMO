package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SomePassenger extends SomePerson implements Passenger {
    private PassengerCondition condition;
    private final Station destination;
    private final double depthOfSleep;
    private List<Thing> baggage = new ArrayList<>();
    private Actions actions;

    public SomePassenger(String name, PassengerCondition condition, Station destination, double depthOfSleep,
                         Actions actions) {
        super(name);
        super.setType(PersonTypes.PASSENGER);
        this.destination = destination;
        this.condition = condition;
        this.depthOfSleep = depthOfSleep;
        this.actions = actions;
    }

    public SomePassenger(String name, PassengerCondition condition, Station destination, double depthOfSleep,
                         List<Thing> baggage, Actions actions) {
        super(name);
        super.setType(PersonTypes.PASSENGER);
        this.destination = destination;
        this.condition = condition;
        this.depthOfSleep = depthOfSleep;
        this.baggage = baggage;
        this.actions = actions;
    }

    public Station getDestination() {
        return destination;
    }

    public void leave(Train train) {
        train.removePassenger(this);
    }

    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof SomePassenger)) return false;
        SomePassenger passenger = (SomePassenger) that;
        return Objects.equals(passenger.destination, destination) && Objects.equals(getName(), passenger.getName())
                && Objects.equals(getType(), passenger.getType())
                && Objects.equals(getCondition(), passenger.getCondition());
    }

    public void commentOnLeavingTrain(Station station) {
        System.out.print(this + " leaves the train at " + station + ".\n");
        if(station != destination) {
            if(!condition.equals(PassengerCondition.SHOCKED))
                say("Hey! That's not my station!");
            else
                System.out.println(this + " wants to say something but is too shocked to open his mouth.");
        }
    }

    public void sleep() {
        System.out.print(this+ " is sleeping. ");
        say("Zzz..");
    }

    public void setCondition(PassengerCondition condition) {
        this.condition = condition;
        commentOnSetCondition(condition);
    }

    protected void commentOnSetCondition(PassengerCondition condition){
        System.out.print(this + " is now " + condition + ". \n");
    }

    public void fallAsleepTime(TimeCounter counter) {
        if(!condition.equals(PassengerCondition.ASLEEP)) {
            System.out.print(this + " falls asleep because the time is " + counter + "\n");
            setCondition(PassengerCondition.ASLEEP);
        }
    }

    public List<Thing> getBaggage() {
        return baggage;
    }

    public Actions getActions() {
        return actions;
    }

    public double getDepthOfSleep() {
        return this.depthOfSleep;
    }

    public PassengerCondition getCondition() {
        return condition;
    }

    public boolean isAsleep() {
        return (getCondition() == PassengerCondition.ASLEEP);
    }

    public int hashCode() {
        return Objects.hash(getCondition(), getName(), getType(), destination);
    }
}
