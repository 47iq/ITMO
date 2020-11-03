package com.company;

public enum PersonTypes {
    passenger{
        public String toString() {
            return "Passenger";
        }
    },
    conductor{
        public String toString() {
            return "Conductor";
        }
    }
}
