package com.company;

public enum PassengerCondition {
    ASLEEP{
        public String toString() {
            return "asleep";
        }
    },
    REGULAR_AWAKE {
    //REGULAR_AWAKE = !ASLEEP with no emotions
        public String toString() {
            return "awake";
        }
    },
    SHOCKED{
        public String toString() {
            return "shocked";
        }
    },
    WOUNDED_IN_FOREHEAD{
        public String toString() {
            return "having a big bump on the head";
        }
    },
    LIGHT_WOUNDED_IN_FOREHEAD{
        public String toString() {
            return "having a small bump on the head";
        }
    },
    SATISFIED{
        public String toString() {
            return "satisfied";
        }
    },
    HAPPY{
        public String toString() {
            return "happy";
        }
    }
}
