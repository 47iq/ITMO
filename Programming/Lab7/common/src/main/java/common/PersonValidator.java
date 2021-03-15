package common;

public interface PersonValidator {
    HairColor castHairColor(String hairColor);

    Country castCountry(String nationality);

    EyesColor castEyesColor(String eyeColor);

    Long castWeight(String weight);
}
