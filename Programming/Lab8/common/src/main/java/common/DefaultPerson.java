package common;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class DefaultPerson implements Person, Serializable {

    @Serial
    private static final long serialVersionUID = 10L;

    private Long weight; //Поле может быть null, Значение поля должно быть больше 0
    private EyesColor eyeColor; //Поле не может быть null
    private HairColor hairColor; //Поле не может быть null
    private Country nationality; //Поле не может быть null

    private transient static PersonValidator validator;

    DefaultPerson(Person person) {
        this.weight = person.getWeight();
        this.eyeColor = person.getEyeColor();
        this.hairColor = person.getHairColor();
        this.nationality = person.getNationality();
    }

    protected DefaultPerson() {
    }

    public static DefaultPerson convert(Person person) {
        return new DefaultPerson(person);
    }

    public static void setValidator(PersonValidator validator) {
        DefaultPerson.validator = validator;
    }

    @Override
    public String toString() {
        return "Person" + weight + " " + eyeColor + " " + hairColor + " " + nationality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(weight, person.getWeight()) &&
                eyeColor == person.getEyeColor() &&
                hairColor == person.getHairColor() &&
                nationality == person.getNationality();
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, eyeColor, hairColor, nationality);
    }

    /**
     * Getter for {@link #weight}
     *
     * @return {@link #weight}
     */

    @Override
    public Long getWeight() {
        return weight;
    }

    /**
     * Getter for {@link #eyeColor}
     *
     * @return {@link #eyeColor}
     */

    @Override
    public EyesColor getEyeColor() {
        return eyeColor;
    }

    /**
     * Getter for {@link #hairColor}
     *
     * @return {@link #hairColor}
     */

    @Override
    public HairColor getHairColor() {
        return hairColor;
    }

    /**
     * Getter for {@link #nationality}
     *
     * @return {@link #nationality}
     */

    @Override
    public Country getNationality() {
        return nationality;
    }

    @Override
    public void setWeight(Long weight) {
        this.weight = weight;
    }

    @Override
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
        setWeight(validator.castWeight(weight));
    }

    public void setEyeColorStr(String eyeColor) {
        setEyeColor(validator.castEyesColor(eyeColor));
    }

    public void setHairColorStr(String hairColor) {
        setHairColor(validator.castHairColor(hairColor));
    }

    public void setNationalityStr(String nationality) {
        setNationality(validator.castCountry(nationality));
    }
}
