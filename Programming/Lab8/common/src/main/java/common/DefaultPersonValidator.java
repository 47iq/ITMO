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

    @Override
    public HairColor castHairColor(String colorStr) {
        return switch (colorStr.toLowerCase()) {
            case "black" -> HairColor.BLACK;
            case "red" -> HairColor.RED;
            case "yellow" -> HairColor.YELLOW;
            case "green" -> HairColor.GREEN;
            default -> throw new InvalidHairColorException();
        };
    }

    /**
     * Method which casts {@link String} to {@link EyesColor}
     *
     * @param colorStr eyes color in string format
     * @return eyesColor {@link EyesColor}
     */

    @Override
    public EyesColor castEyesColor(String colorStr) {
        return switch ((colorStr.toLowerCase())) {
            case "black" -> EyesColor.BLACK;
            case "blue" -> EyesColor.BLUE;
            case "yellow" -> EyesColor.YELLOW;
            default -> throw new InvalidEyesColorException();
        };
    }

    /**
     * Method which casts {@link String} to {@link Country}
     *
     * @param countryStr country in string format
     * @return country {@link Country}
     */

    @Override
    public Country castCountry(String countryStr) {
        return switch (countryStr.toLowerCase()) {
            case "russia" -> Country.RUSSIA;
            case "china" -> Country.CHINA;
            case "spain" -> Country.SPAIN;
            case "france" -> Country.FRANCE;
            default -> throw new InvalidCountryException();
        };
    }

    /**
     * Method which casts {@link String} to {@link Long}
     *
     * @param weight weight in string format
     * @return weight {@link Long}
     */

    @Override
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
