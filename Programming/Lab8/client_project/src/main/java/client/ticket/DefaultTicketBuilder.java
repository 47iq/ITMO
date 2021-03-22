package client.ticket;

import client.ClientContext;
import client.ObjectFactory;
import common.Coordinates;
import common.Person;
import common.Ticket;

public class DefaultTicketBuilder implements TicketBuilder{

    private Ticket ticket;
    private Coordinates coordinates;
    private Person person;
    private final ObjectFactory factory;

    public DefaultTicketBuilder(ObjectFactory factory) {
        this.factory = factory;
        ticket = factory.getDefaultTicket();
        coordinates = factory.getDefaultCoordinates();
        person = factory.getDefaultPerson();
    }

    public void setName(String name) {
        ticket.setName(name);
    }

    public void setX(String x) {
        coordinates.setXStr(x);
    }

    public void setY(String y) {
        coordinates.setYStr(y);
    }

    public void setPrice(String price) {
        ticket.setPriceStr(price);
    }

    public void setDiscount(String discount) {
        ticket.setDiscountStr(discount);
    }

    public void setRefundable(String refundable) {
        ticket.setRefundableStr(refundable);
    }

    public void setType(String type) {
        ticket.setTypeStr(type);
    }

    public void setWeight(String weight) {
        person.setWeightStr(weight);
    }

    public void setEyeColor(String eyeColor) {
        person.setEyeColorStr(eyeColor);
    }

    public void setHairColor(String hairColor) {
        person.setHairColorStr(hairColor);
    }

    public void setCountry(String country) {
        person.setNationalityStr(country);
    }

    public Ticket getResult() {
        ticket.setPerson(person);
        ticket.setCoordinates(coordinates);
        return ticket;
    }

    public void reset() {
        ticket = factory.getDefaultTicket();
        coordinates = factory.getDefaultCoordinates();
        person = factory.getDefaultPerson();
    }
}
