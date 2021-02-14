package main;

import org.json.simple.JSONObject;

import java.time.ZonedDateTime;
import java.util.Objects;

public abstract class AbstractTicket implements Comparable<AbstractTicket> {

    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int price; //Значение поля должно быть больше 0
    private double discount; //Значение поля должно быть больше 0, Максимальное значение поля: 100
    private Boolean refundable; //Поле может быть null
    private TicketType type; //Поле может быть null
    private Person person; //Поле не может быть null

    /**
     * Getter for {@link AbstractTicket#id}
     * @return int {@link AbstractTicket#id}
     */

    public int getId() {
        return id;
    }

    /**
     * Getter for {@link AbstractTicket#coordinates}
     * @return {@link Coordinates} {@link AbstractTicket#coordinates}
     */

    Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Getter for {@link AbstractTicket#discount}
     * @return double {@link AbstractTicket#discount}
     */

    public double getDiscount() {
        return discount;
    }

    /**
     * Getter for {@link AbstractTicket#refundable}
     * @return Boolean {@link AbstractTicket#refundable}
     */

    public Boolean getRefundable() {
        return refundable;
    }

    /**
     * Setter for {@link AbstractTicket#id} used to update tickets with given id's
     * @param id int
     */

    public void configureId(int id) {
        this.id = id;
    }

    /**
     * Setter for ticket {@link AbstractTicket#type}
     * @param type {@link TicketType}
     */
    public void setType(TicketType type) {
        this.type = type;
    }

    /**
     * Getter for the ticket {@link AbstractTicket#price}
     * @return int price
     */

    public int getPrice() {
        return price;
    }

    /**
     * A {@link AbstractTicket#price} validator
     * @param price int
     * @return true if price is valid, false if not
     */

    static boolean priceValid(int price) {
        return price > 0;
    }

    /**
     * A {@link AbstractTicket#name} validator
     * @param name String
     * @return true if name is valid, false if not
     */

    static boolean nameValid(String name) {
        return name != null && !name.equals("");
    }

    /**
     * A {@link AbstractTicket#discount} validator
     * @param discount double
     * @return true if discount is valid, false if not
     */

    static boolean discountValid(double discount) {
        return discount > 0 && discount <= 100;
    }

    static boolean idValid(int id) {
        return id > 0;
    }

    /**
     * Method which casts String type into main.TicketType
     * @param typeStr Type in the String format
     * @return Type in main.TicketType format
     */

    static TicketType castType(String typeStr) {
        return switch (typeStr.toLowerCase()) {
            case "vip" -> TicketType.VIP;
            case "cheap" -> TicketType.CHEAP;
            case "usual" -> TicketType.USUAL;
            case "" -> null;
            default -> throw new exceptions.InvalidTypeException();
        };
    }

    public int compareTo(AbstractTicket ticket) {
        int priceDiff = getPrice() - ticket.getPrice();
        if(priceDiff != 0)
            return priceDiff;
        else
            return getId() - ticket.getId();
    }

    /**
     * Getter for {@link AbstractTicket#person}
     * @return {@link Person} person
     */

    public Person getPerson() {
        return person;
    }

    /**
     * Getter for {@link #creationDate}
     * @return {@link ZonedDateTime} {@link #creationDate}
     */

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Getter for {@link #name}
     * @return {@link String} {@link #name}
     */

    public String getName() {
        return name;
    }

    /**
     * Getter for {@link #type}
     * @return {@link TicketType} {{@link #type}}
     */

    public TicketType getType(){
        return type;
    }

    /**
     * Setter for {@link #creationDate}
     * @param creationDate {@link ZonedDateTime}
     */

    protected void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String toString() {
        return "AbstractTicket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractTicket ticket = (AbstractTicket) o;
        return id == ticket.id &&
                price == ticket.price &&
                Double.compare(ticket.discount, discount) == 0 &&
                name.equals(ticket.name) &&
                coordinates.equals(ticket.coordinates) &&
                creationDate.equals(ticket.creationDate) &&
                Objects.equals(refundable, ticket.refundable) &&
                type == ticket.type &&
                person.equals(ticket.person);
    }

    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, price, discount, refundable, type, person);
    }

    /**
     * Setter for {@link #name}
     * @param name {@link String}
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for {@link #coordinates}
     * @param coordinates {@link Coordinates}
     */

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Setter for {@link #price}
     * @param price int
     */

    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Setter for {@link #discount}
     * @param discount double
     */

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * Setter for {@link #refundable}
     * @param refundable {@link Boolean}
     */
    public void setRefundable(Boolean refundable) {
        this.refundable = refundable;
    }

    /**
     * Setter for {@link #person}
     * @param person {@link Person}
     */

    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Method used to convert ticket's data into JSON format
     * @return {@link JSONObject} that contains data
     */

    public JSONObject toJSON() {
        JSONObject jsonTicket = new JSONObject();
        JSONObject jsonCoordinates = new JSONObject();
        JSONObject jsonPerson = new JSONObject();
        jsonTicket.put("id", Integer.toString(id));
        jsonTicket.put("name", name);
        jsonCoordinates.put("x", Double.toString(coordinates.getX()));
        jsonCoordinates.put("y", coordinates.getY().toString());
        jsonTicket.put("coordinates",jsonCoordinates);
        jsonTicket.put("creationDate", creationDate.toString());
        jsonTicket.put("price", Integer.toString(price));
        jsonTicket.put("discount", Double.toString(discount));
        if(refundable != null)
            jsonTicket.put("refundable", refundable.toString());
        if(type != null)
            jsonTicket.put("type", type.toString());
        jsonPerson.put("weight", person.getWeight().toString());
        jsonPerson.put("eyeColor", person.getEyeColor().toString());
        jsonPerson.put("hairColor", person.getHairColor().toString());
        jsonPerson.put("nationality", person.getNationality().toString());
        jsonTicket.put("person", jsonPerson);
        return jsonTicket;
    }
}
