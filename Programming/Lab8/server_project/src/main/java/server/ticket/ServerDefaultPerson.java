package server.ticket;

import common.Country;
import common.DefaultPerson;
import common.EyesColor;
import common.HairColor;

/**
 * Class which stores and manages person's data
 *
 * @version 1.0
 * @autor 47iq
 */


public class ServerDefaultPerson extends DefaultPerson {
    public ServerDefaultPerson(Long weight, EyesColor eyesColor, HairColor hairColor, Country country) {
        setWeight(weight);
        setEyeColor(eyesColor);
        setHairColor(hairColor);
        setNationality(country);
    }

    public ServerDefaultPerson() {

    }
}