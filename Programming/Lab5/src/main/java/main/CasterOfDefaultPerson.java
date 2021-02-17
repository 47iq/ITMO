package main;

import exceptions.InvalidCountryException;
import exceptions.InvalidEyesColorException;
import exceptions.InvalidHairColorException;
import exceptions.InvalidWeightException;

public interface CasterOfDefaultPerson {
    /**
     * Method which casts {@link String} to {@link HairColor}
     * @param colorStr hair color in string format
     * @return hairColor {@link HairColor}
     */

    default HairColor castHairColor(String colorStr) {
        switch (colorStr.toLowerCase()) {
            case "black" : return  HairColor.BLACK;
            case "red" : return HairColor.RED;
            case "yellow" : return  HairColor.YELLOW;
            case "green" : return  HairColor.GREEN;
            default : throw new InvalidHairColorException();
        }
    }

    /**
     * Method which casts {@link String} to {@link EyesColor}
     * @param colorStr eyes color in string format
     * @return eyesColor {@link EyesColor}
     */

    default EyesColor castEyesColor(String colorStr) {
        switch ((colorStr.toLowerCase())) {
            case "black" : return  EyesColor.BLACK;
            case "blue" : return  EyesColor.BLUE;
            case "yellow" : return  EyesColor.YELLOW;
            default : throw new InvalidEyesColorException();
        }
    }

    /**
     * Method which casts {@link String} to {@link Country}
     * @param countryStr country in string format
     * @return country {@link Country}
     */

    default Country castCountry(String countryStr) {
        switch (countryStr.toLowerCase()) {
            case "russia" : return  Country.RUSSIA;
            case "china" : return  Country.CHINA;
            case "spain" : return  Country.SPAIN;
            case  "france" : return  Country.FRANCE;
            default : throw new InvalidCountryException();
        }
    }

    /**
     * Method which casts {@link String} to {@link Long}
     * @param weight weight in string format
     * @return weight {@link Long}
     */

    default Long castWeight(String weight) {
        Long weightLong = Long.parseLong(weight);
        if(weightValid(weightLong))
            return weightLong;
        else
            throw new InvalidWeightException();
    }

    /**
     * Weight validator
     * @param weight {@link Long}
     * @return true if valid, false if not
     */

    default boolean weightValid(Long weight) {
        return weight > 0;
    }
}
