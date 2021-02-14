package main;

import exceptions.InvalidCountryException;
import exceptions.InvalidEyesColorException;
import exceptions.InvalidHairColorException;
import exceptions.InvalidWeightException;
import org.json.simple.JSONObject;

import java.util.Objects;

/**
 * Class which stores and manages person's data
 * @autor 47iq
 * @version 1.0
 */


public class Person {
    private Long weight; //Поле может быть null, Значение поля должно быть больше 0
    private EyesColor eyeColor; //Поле не может быть null
    private HairColor hairColor; //Поле не может быть null
    private Country nationality; //Поле не может быть null

    /**
     * Constructor to get person from {@link JSONObject}
     * @param jsonObject {@link JSONObject}
     */

    public Person(JSONObject jsonObject){
        Object weightJSON = jsonObject.get("weight");
        if(weightJSON != null)
            this.weight = castWeight((String) weightJSON);
        else
            weight = null;
        if(jsonObject.get("eyeColor") == null)
            throw new InvalidEyesColorException();
        else this.eyeColor = castEyesColor((String) jsonObject.get("eyeColor"));
        if(jsonObject.get("hairColor") == null)
            throw new InvalidHairColorException();
        else this.hairColor = castHairColor((String) jsonObject.get("hairColor"));
        if(jsonObject.get("nationality") == null)
            throw new InvalidCountryException();
        else this.nationality = castCountry((String) jsonObject.get("nationality"));
    }

    /**
     * Constructor for getting {@link Person} from its fields
     * @param weight {@link Person#weight}
     * @param eyesColor {@link Person#eyeColor}
     * @param hairColor {@link Person#hairColor}
     * @param country {@link Person#nationality}
     */

    Person(Long weight, EyesColor eyesColor, HairColor hairColor, Country country) {
        this.weight = weight;
        this.eyeColor = eyesColor;
        this.hairColor = hairColor;
        this.nationality = country;
    }

    /**
     * Method which casts {@link String} to {@link HairColor}
     * @param colorStr {@link String}
     * @return hairColor {@link HairColor}
     */

    public static HairColor castHairColor(String colorStr) {
        switch (colorStr.toLowerCase()) {
            case "black" : return  HairColor.BLACK;
            case "red" : return HairColor.RED;
            case "yellow" : return  HairColor.YELLOW;
            case "green" : return  HairColor.GREEN;
            default : throw new InvalidHairColorException();
        }
    }

    /**
     * Method which casts {@link String} to {@link EyesColor}
     * @param colorStr {@link String}
     * @return eyesColor {@link EyesColor}
     */

    public static EyesColor castEyesColor(String colorStr) {
        switch ((colorStr.toLowerCase())) {
            case "black" : return  EyesColor.BLACK;
            case "blue" : return  EyesColor.BLUE;
            case "yellow" : return  EyesColor.YELLOW;
            default : throw new InvalidEyesColorException();
        }
    }

    /**
     * Method which casts {@link String} to {@link Country}
     * @param countryStr {@link String}
     * @return country {@link Country}
     */

    public static Country castCountry(String countryStr) {
        switch (countryStr.toLowerCase()) {
            case "russia" : return  Country.RUSSIA;
            case "china" : return  Country.CHINA;
            case "spain" : return  Country.SPAIN;
            case  "france" : return  Country.FRANCE;
            default : throw new InvalidCountryException();
        }
    }

    /**
     * Method which casts {@link String} to {@link Long}
     * @param weight {@link String}
     * @return weight {@link Long}
     */

    private Long castWeight(String weight) {
        Long weightLong = Long.parseLong(weight);
        if(weightValid(weightLong))
            return weightLong;
        else
            throw new InvalidWeightException();
    }

    /**
     * Weight validator
     * @param weight {@link Long}
     * @return true if valid, false if not
     */

    public static boolean weightValid(Long weight) {
        return weight > 0;
    }

    public String toString() {
        return "Person" + weight + " " + eyeColor + " " + hairColor + " " + nationality;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(weight, person.weight) &&
                eyeColor == person.eyeColor &&
                hairColor == person.hairColor &&
                nationality == person.nationality;
    }

    public int hashCode() {
        return Objects.hash(weight, eyeColor, hairColor, nationality);
    }

    /**
     * Getter for {@link #weight}
     * @return {@link #weight}
     */

    public Long getWeight() {
        return weight;
    }

    /**
     * Getter for {@link #eyeColor}
     * @return {@link #eyeColor}
     */

    public EyesColor getEyeColor() {
        return eyeColor;
    }

    /**
     * Getter for {@link #hairColor}
     * @return {@link #hairColor}
     */

    public HairColor getHairColor() {
        return hairColor;
    }

    /**
     * Getter for {@link #nationality}
     * @return {@link #nationality}
     */

    public Country getNationality() {
        return nationality;
    }
}