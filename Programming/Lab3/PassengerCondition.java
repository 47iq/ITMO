package com.company;

public enum PassengerCondition {
    ASLEEP {
        public String toString() {
            return "asleep";
        }
    },
    AWAKE {
        public String toString() {
            return "awake";
        }
    };
}
