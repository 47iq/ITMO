package com.company;

import java.util.Objects;

public class ScuperfieldActions implements Actions{
    private Passenger passenger;

    public ScuperfieldActions() {
    }

    class Bottle extends SomeThingPattern{
        private final String liquid;
        private boolean IsSpilling = false;
        private int pitch = 0;
        private boolean hasFallen = false;
        private String liquidDescription;

        Bottle(String name, String liquid, double price, int pitch, String liquidDescription) throws UnableToFall {
            super(name, price);
            this.liquid = liquid;
            this.liquidDescription = liquidDescription;
            for(int i = 0; i < pitch; i++)
                tilt();
        }

        public void tilt() throws UnableToFall {
            System.out.println(this + " tilts.");
            pitch++;
            if(pitch > 2)
                fallOnPersonsHead();
            else if(pitch > 1)
                spillOnPerson();
            else if(pitch == 1)
                spill();
        }

        public void fallOnPersonsHead() throws UnableToFall{
            if(hasFallen)
                throw new UnableToFall(this + " has already fallen!");
            String s = "";
            System.out.println(this + " falls on " + passenger + "'s head" + s + ".");
            System.out.println(this + " hits " + passenger + "'s forehead.");
            double hitPower = Math.random() * 10;
            if(hitPower < 5)
                    passenger.setCondition(PassengerCondition.LIGHT_WOUNDED_IN_FOREHEAD);
            else
                    passenger.setCondition(PassengerCondition.WOUNDED_IN_FOREHEAD);
            hasFallen = true;
        }

        public void spill() {
            System.out.println(getLiquid() + " spills from " + this + ".");
            IsSpilling = true;
        }

        public void spillOnPerson(){
            if(IsSpilling) {
                System.out.println(getLiquid() + " from " + this + " spills on " + passenger + ".");
                System.out.println(passenger + " drinks " + liquidDescription + " " + getLiquid() + " from " + this + ".");
            }
        }

        public String getLiquid() {
            return liquid;
        }

        public boolean equals(Object that) {
            if (this == that) return true;
            if (!(that instanceof Bottle)) return false;
            Bottle bottle = (Bottle) that;
            return Objects.equals(bottle.getName(), getName())
                    && Objects.equals(bottle.getLiquid(), liquid) && Objects.equals(bottle.getPrice(), getPrice())
                    && Boolean.compare(bottle.IsSpilling, IsSpilling) == 1;
        }

        public String toString() {
            return getName();
        }

        public int hashCode() {
            return Objects.hash(getName(), getPrice(), getLiquid(), IsSpilling);
        }
    }

    public void completeActions(Passenger passenger) throws UnableToFall {
        this.passenger = passenger;
        if(!passenger.getName().equals("Scuperfield"))
            throw new IllegalActionsTarget("You can only complete ScuperfieldActions for passengers named Scuperfield.");

        class Cane extends SomeThingPattern{
            Cane(String name, double price) {
                super(name, price);
                System.out.println("//Previously " + passenger + " lost his " + this + " which costs " +
                        (int) this.getPrice() + ".");
            }
        }

        Cane lostCane = new Cane("Cane", 10000);
        Bottle bottle = new Bottle("Bottle", "Soda", 10, 2, "sweet, great smelling, " +
                "pleasantly pinching mouth");
        makeCalculations(bottle, lostCane);
        bottle.tilt();
    }

    private void makeCalculations(Thing thing, Thing secondThing) {
        calcPrice(thing);
        sub(thing.getPrice(), secondThing.getPrice());
        calculateNum(thing.getPrice(), secondThing.getPrice());
    }

    private void calcPrice(Thing thing) {
        double res = thing.getPrice();
        passenger.think("I would have to pay " + (int) res + " for this " + thing);
    }

    private void sub(double price, double price0) {
        double res = price0 - price;
        passenger.think("The sum of my loss has lowered. It's only " + (int) res + " now!");
        passenger.setCondition(PassengerCondition.SATISFIED);
    }

    private void calculateNum(double price, double price0) {
        double res = price0 / price;
        if (price0 % price > 0)
            res++;
        String MIDDLE = "trips";
        if(res == 1)
            MIDDLE = "trip";
        passenger.think("I have to take only " + (int) res + " train " + MIDDLE + " to get my money back!");
        passenger.setCondition(PassengerCondition.HAPPY);
    }
}
