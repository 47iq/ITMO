package com.company;

public enum PersonTypes {
    PASSENGER {
        public String toString() {
            return "Passenger";
        }
    },
    CONDUCTOR {
        public String toString() {
            return "Conductor";
        }
    }
}
