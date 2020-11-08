package com.company;

public enum TrainCondition{
    STAYING {
        public String toString() {
            return "staying";
        }
    },
    MOVING {
        public String toString() {
            return "moving";
        }
    }
}
