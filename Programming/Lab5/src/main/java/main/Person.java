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


public class Person implements PersonCaster{
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