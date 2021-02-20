package main.ticket;

/**
 * Class which stores and manages person's data
 * @autor 47iq
 * @version 1.0
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