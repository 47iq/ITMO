package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SomeConductor extends SomePerson implements Conductor {
    private double forgetfulness;
    private final Tongs tongs = new Tongs();

    static class Tongs extends SomeThingPattern {
        public Tongs() {
            super("tongs");
        }
    }

    public SomeConductor(String s, double forgetfulness) {
        super(s);
        super.setType(PersonTypes.CONDUCTOR);
        this.forgetfulness = forgetfulness;
    }

    public void checkPassengersOut(Train train) {
        Station station = train.getStation();
        Station prevStation = train.getPrevStation();
        ArrayList<Passenger> passengers= train.getPassengers();
        ArrayList<Passenger> out = new ArrayList<>();
        for(Passenger passenger: passengers) {
            if(passenger.getDestination().equals(station) && Math.random() > forgetfulness
                    || passenger.getDestination().equals(prevStation)){
                out.add(passenger);
            }
        }
        for(Passenger passenger: out) {
            remindToLeave(passenger, train);
        }
    }

    public void doubleCheck(Train train) {
        Station station = train.getStation();
        ArrayList<Passenger> passengers= train.getPassengers();
        int counter = 0;
        System.out.print(this + " checks if he forgot to tell anybody to leave the train.\n");
        for(Passenger passenger: passengers) {
            String START = "he";
            String MIDDLE = "his";
            if(passenger instanceof Groupable){
                START = "they";
                MIDDLE = "their";
            }
            if(passenger.getDestination().equals(station)){
                System.out.print("Oh no! " + this + " forgot to tell " + passenger + " to leave.\n");
                counter++;
                System.out.print(this + " decides to wait till the next station and not to say " + passenger
                            + " that " + START +" skipped " + MIDDLE + " station, because " + this +" wants to avoid explanations.\n");
                this.forgetfulness = 0;
                System.out.print(this + " tries to get more concentrated. He won't forget about any passenger from now on.\n");
            }
        }
        if(counter == 0)
            System.out.print(this + " didn't forget anyone.\n");
    }

    private void remindToLeave(Passenger passenger, Train train) {
        say(passenger + ", you have to leave now!");
        if(!passenger.getCondition().equals(PassengerCondition.ASLEEP)) {
            passenger.leave(train);
        }
        else {
            String MIDDLE = "is";
            if(passenger instanceof Groupable)
                MIDDLE = "are";
                System.out.println(passenger + " didn't hear " + this + " cause " + passenger + " " + MIDDLE + " sleeping.");
            shake(passenger, train);
        }
    }

    private void throwBaggageOut(Passenger passenger) {
        List<Thing> baggage = passenger.getBaggage();
        List<Thing> out = new ArrayList<>();
        if(!baggage.isEmpty()) {
            for(Thing thing: baggage) {
                System.out.println(this + " throws out " + passenger + "'s " + thing + ".");
                out.add(thing);
            }
            for(Thing thing: out) {
                baggage.remove(thing);
            }
        }
    }

    private void shake(Passenger passenger, Train train) {
        System.out.println(this + " shakes " + passenger + ".");
        double depthOfSleep = passenger.getDepthOfSleep();
        if(depthOfSleep < 3) {
                System.out.print(this + " wakes " + passenger + " up.\n");
                passenger.setCondition(PassengerCondition.REGULAR_AWAKE);
        }
        else {
            String BEGINNING = "";
            String ENDING = "";
            String MIDDLE;
            if(passenger instanceof Groupable){
                BEGINNING += (passenger + " are ");
                ENDING += "their shelves";
                MIDDLE = "they";
            }
            else {
                BEGINNING += (passenger + " is ");
                ENDING += "his shelf";
                MIDDLE = "he";
            }
            if(depthOfSleep < 6) {
                System.out.println(BEGINNING + "still sleeping. " + this + " takes out " + tongs
                        + " and starts knocking on " + ENDING + ".");
                passenger.setCondition(PassengerCondition.REGULAR_AWAKE);
                passenger.leave(train);
            }
            else {
                System.out.println(BEGINNING + "still sleeping. " + this + " realizes that " + MIDDLE +
                        " won't wake up and gets angry. " + this + " shouts into " + passenger + "'s ear.");
                passenger.setCondition(PassengerCondition.REGULAR_AWAKE);
                kickOutOfTrain(passenger, train);
                throwBaggageOut(passenger);
            }
        }
    }

    private void kickOutOfTrain(Passenger passenger, Train train) {
        System.out.println(this + " kicks " + passenger + " out of the train.");
        passenger.setCondition(PassengerCondition.SHOCKED);
        passenger.leave(train);
    }

    public boolean equals(Object that) {
        if (this == that) return true;
        if (!(that instanceof SomeConductor)) return false;
        SomeConductor conductor = (SomeConductor) that;
        return Double.compare(conductor.forgetfulness, forgetfulness) == 0
                && Objects.equals(getName(), conductor.getName()) && Objects.equals(getType(), conductor.getType());
    }

    public String toString() {
        return getName();
    }

    public int hashCode() {
        return Objects.hash(getName(), getType(), forgetfulness);
    }
}
