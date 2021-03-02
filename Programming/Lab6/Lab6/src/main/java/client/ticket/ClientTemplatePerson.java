package client.ticket;

import common.Country;
import common.DefaultPerson;
import common.EyesColor;
import common.HairColor;

public class ClientTemplatePerson extends DefaultPerson {

    public ClientTemplatePerson(Long weight, EyesColor eyesColor, HairColor hairColor, Country country) {
        setWeight(weight);
        setEyeColor(eyesColor);
        setHairColor(hairColor);
        setNationality(country);
    }

    public ClientTemplatePerson() {

    }
}
