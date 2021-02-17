package main;

import exceptions.InvalidCountryException;
import exceptions.InvalidEyesColorException;
import exceptions.InvalidHairColorException;
import org.json.simple.JSONObject;

/**
 * Class which stores and manages person's data
 * @autor 47iq
 * @version 1.0
 */


public class DefaultPerson extends AbstractPerson implements CasterOfDefaultPerson {

    /**
     * Constructor to get person from {@link JSONObject}
     * @param jsonObject {@link JSONObject}
     */

    public DefaultPerson(JSONObject jsonObject){
        Object weightJSON = jsonObject.get("weight");
        if(weightJSON != null)
            setWeight(castWeight((String) weightJSON));
        else
            setWeight(null);
        if(jsonObject.get("eyeColor") == null)
            throw new InvalidEyesColorException();
        else setEyeColor(castEyesColor((String) jsonObject.get("eyeColor")));
        if(jsonObject.get("hairColor") == null)
            throw new InvalidHairColorException();
        else setHairColor(castHairColor((String) jsonObject.get("hairColor")));
        if(jsonObject.get("nationality") == null)
            throw new InvalidCountryException();
        else setNationality(castCountry((String) jsonObject.get("nationality")));
    }

    DefaultPerson(Long weight, EyesColor eyesColor, HairColor hairColor, Country country) {
        setWeight(weight);
        setEyeColor(eyesColor);
        setHairColor(hairColor);
        setNationality(country);
    }
}