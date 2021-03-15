package common;

public interface Person {

    Long getWeight();

    EyesColor getEyeColor();

    HairColor getHairColor();

    Country getNationality();

    void setWeight(Long weight);

    void setEyeColor(EyesColor eyeColor);

    void setHairColor(HairColor hairColor);

    void setNationality(Country nationality);
}

