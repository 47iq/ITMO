package common;

import server.exceptions.InvalidCountryException;
import server.exceptions.InvalidEyesColorException;
import server.exceptions.InvalidHairColorException;
import server.exceptions.InvalidWeightException;

public class DefaultPersonValidator implements PersonValidator {
    /**
     * Method which casts {@link String} to {@link HairColor}
     *
     * @param colorStr hair color in string format
     * @return hairColor {@link HairColor}
     */

    public HairColor castHairColor(String colorStr) {
        switch (colorStr.toLowerCase()) {
            case "black":
                return HairColor.BLACK;
            case "red":
                return HairColor.RED;
            case "yellow":
                return HairColor.YELLOW;
            case "green":
                return HairColor.GREEN;
            default:
                throw new InvalidHairColorException();
        }
    }

    /**
     * Method which casts {@link String} to {@link EyesColor}
     *
     * @param colorStr eyes color in string format
     * @return eyesColor {@link EyesColor}
     */

    public EyesColor castEyesColor(String colorStr) {
        switch ((colorStr.toLowerCase())) {
            case "black":
                return EyesColor.BLACK;
            case "blue":
                return EyesColor.BLUE;
            case "yellow":
                return EyesColor.YELLOW;
            default:
                throw new InvalidEyesColorException();
        }
    }

    /**
     * Method which casts {@link String} to {@link Country}
     *
     * @param countryStr country in string format
     * @return country {@link Country}
     */

    public Country castCountry(String countryStr) {
        switch (countryStr.toLowerCase()) {
            case "russia":
                return Country.RUSSIA;
            case "china":
                return Country.CHINA;
            case "spain":
                return Country.SPAIN;
            case "france":
                return Country.FRANCE;
            default:
                throw new InvalidCountryException();
        }
    }

    /**
     * Method which casts {@link String} to {@link Long}
     *
     * @param weight weight in string format
     * @return weight {@link Long}
     */

    public Long castWeight(String weight) {
        if (weight == null || weight.equals(""))
            return null;
        Long weightLong = Long.parseLong(weight);
        if (weightValid(weightLong))
            return weightLong;
        else
            throw new InvalidWeightException();
    }

    /**
     * Weight validator
     *
     * @param weight {@link Long}
     * @return true if valid, false if not
     */

    boolean weightValid(Long weight) {
        return weight > 0;
    }
}
