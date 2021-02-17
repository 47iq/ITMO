package ticket;

import org.json.simple.JSONObject;

import java.time.ZonedDateTime;
import java.util.Objects;

public abstract class AbstractTicket implements Ticket {

    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int price; //Значение поля должно быть больше 0
    private double discount; //Значение поля должно быть больше 0, Максимальное значение поля: 100
    private Boolean refundable; //Поле может быть null
    private TicketType type; //Поле может быть null
    private Person person; //Поле не может быть null

    /**
     * Getter for {@link AbstractTicket#id}
     * @return {@link AbstractTicket#id}
     */

    public int getId() {
        return id;
    }

    /**
     * Getter for {@link AbstractTicket#coordinates}
     * @return {@link #coordinates}
     */

    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Getter for {@link AbstractTicket#discount}
     * @return {@link AbstractTicket#discount}
     */

    public double getDiscount() {
        return discount;
    }

    /**
     * Getter for {@link AbstractTicket#refundable}
     * @return {@link AbstractTicket#refundable}
     */

    public Boolean getRefundable() {
        return refundable;
    }

    /**
     * Setter for {@link AbstractTicket#id} used to update tickets with given id's
     * @param id ticket id
     */

    public void configureId(int id) {
        this.id = id;
    }

    /**
     * Setter for ticket {@link AbstractTicket#type}
     * @param type ticket's type
     */
    public void setType(TicketType type) {
        this.type = type;
    }

    /**
     * Getter for the ticket {@link AbstractTicket#price}
     * @return {@link #price}
     */

    public int getPrice() {
        return price;
    }


    /**
     * Getter for {@link AbstractTicket#person}
     * @return {@link AbstractTicket#person}
     */

    public Person getPerson() {
        return person;
    }

    /**
     * Getter for {@link #creationDate}
     * @return {@link #creationDate}
     */

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Getter for {@link #name}
     * @return {@link #name}
     */

    public String getName() {
        return name;
    }

    /**
     * Getter for {@link #type}
     * @return {{@link #type}}
     */

    public TicketType getType(){
        return type;
    }

    /**
     * Setter for {@link #creationDate}
     * @param creationDate ticket creation date
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
     * @param name ticket name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for {@link #coordinates}
     * @param coordinates ticket coordinates
     */

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Setter for {@link #price}
     * @param price ticket price
     */

    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Setter for {@link #discount}
     * @param discount ticket discount
     */

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * Setter for {@link #refundable}
     * @param refundable ticket refundable
     */
    public void setRefundable(Boolean refundable) {
        this.refundable = refundable;
    }

    /**
     * Setter for {@link #person}
     * @param person ticket person
     */

    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Method used to convert ticket's data into JSON format
     * @return {@link JSONObject} that contains data of the ticket
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
        if(refundable == null)
            jsonTicket.put("refundable", "");
        else
            jsonTicket.put("refundable", refundable.toString());
        if(type == null)
            jsonTicket.put("type", "");
        else
            jsonTicket.put("type", type.toString());
        jsonPerson.put("weight", person.getWeight().toString());
        jsonPerson.put("eyeColor", person.getEyeColor().toString());
        jsonPerson.put("hairColor", person.getHairColor().toString());
        jsonPerson.put("nationality", person.getNationality().toString());
        jsonTicket.put("person", jsonPerson);
        return jsonTicket;
    }
}
