package com.company;

import java.util.Objects;

public class Passenger extends Person implements iPassenger, iSleep{
    private PassengerCondition condition;
    private final Station destination;
    private static int ind = 0;

    public Passenger() {
        super();
        String name = "Passenger " + ind;
        setName(name);
        super.setType(PersonTypes.PASSENGER);
        condition = PassengerCondition.AWAKE;
        this.destination = new Station("finalStation");
        ind++;
    }

    public Passenger(String name, Station destination) {
        super(name);
        super.setType(PersonTypes.PASSENGER);
        condition = PassengerCondition.AWAKE;
        this.destination = destination;
    }

    public Passenger(String name, PassengerCondition condition, Station destination) {
        super(name);
        super.setType(PersonTypes.PASSENGER);
        this.destination = destination;
        this.condition = condition;
    }

    public Station getDestination() {
        return destination;
    }

    public void leave(Train train) {
        train.removePassenger(this);
    }

    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof Passenger)) return false;
        Passenger passenger = (Passenger) that;
        return Objects.equals(passenger.destination, destination) && Objects.equals(getName(), passenger.getName())
                && Objects.equals(getType(), passenger.getType())
                && Objects.equals(getCondition(), passenger.getCondition());
    }

    public void sleep() {
        System.out.print(this+ " is sleeping. ");
        say("Zzz..");
    }

    protected void setCondition(PassengerCondition condition) {
        System.out.print(this + " is now " + condition + ". \n");
        this.condition = condition;
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
