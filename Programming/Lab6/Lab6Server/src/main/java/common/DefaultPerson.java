package common;

import java.io.Serializable;
import java.util.Objects;

public class DefaultPerson implements Person, CasterOfDefaultPerson, Serializable {

    private static final long serialVersionUID = 10L;

    private Long weight; //Поле может быть null, Значение поля должно быть больше 0
    private EyesColor eyeColor; //Поле не может быть null
    private HairColor hairColor; //Поле не может быть null
    private Country nationality; //Поле не может быть null

    private DefaultPerson(Person person) {
        this.weight = person.getWeight();
        this.eyeColor = person.getEyeColor();
        this.hairColor = person.getHairColor();
        this.nationality = person.getNationality();
    }

    protected DefaultPerson() {}

    public static DefaultPerson convert(Person person) {
        return new DefaultPerson(person);
    }

    public String toString() {
        return "Person" + weight + " " + eyeColor + " " + hairColor + " " + nationality;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(weight, person.getWeight()) &&
                eyeColor == person.getEyeColor() &&
                hairColor == person.getHairColor() &&
                nationality == person.getNationality();
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

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public void setEyeColor(EyesColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setHairColor(HairColor hairColor) {
        this.hairColor = hairColor;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public void setWeightStr(String weight) {
        setWeight(castWeight(weight));
    }

    public void setEyeColorStr(String eyeColor) {
        setEyeColor(castEyesColor(eyeColor));
    }

    public void setHairColorStr(String hairColor) {
        setHairColor(castHairColor(hairColor));
    }

    public void setNationalityStr(String nationality) {
        setNationality(castCountry(nationality));
    }
}
