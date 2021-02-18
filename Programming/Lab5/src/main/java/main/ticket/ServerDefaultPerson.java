package main.ticket;

import exceptions.InvalidCountryException;
import exceptions.InvalidEyesColorException;
import exceptions.InvalidHairColorException;
import main.CasterOfDefaultPerson;
import org.json.simple.JSONObject;

/**
 * Class which stores and manages person's data
 * @autor 47iq
 * @version 1.0
 */


public class ServerDefaultPerson extends AbstractPerson implements CasterOfDefaultPerson {
    public ServerDefaultPerson(Long weight, EyesColor eyesColor, HairColor hairColor, Country country) {
        setWeight(weight);
        setEyeColor(eyesColor);
        setHairColor(hairColor);
        setNationality(country);
    }
}