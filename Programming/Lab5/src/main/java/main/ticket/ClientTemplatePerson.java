package main.ticket;

public class ClientTemplatePerson extends AbstractPerson{

    public ClientTemplatePerson(Long weight, EyesColor eyesColor, HairColor hairColor, Country country) {
        setWeight(weight);
        setEyeColor(eyesColor);
        setHairColor(hairColor);
        setNationality(country);
    }
}
