package client.ticket;

import client.ClientContext;
import client.ObjectFactory;
import common.Coordinates;
import common.Person;
import common.Ticket;
import server.exceptions.*;

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
        try {
            ticket.setName(name);
        } catch (Exception e) {
            throw new InvalidNameException();
        }
    }

    public void setX(String x) {
        try {
            coordinates.setXStr(x);
        } catch (Exception e) {
            throw new InvalidXCoordinateException();
        }
    }

    public void setY(String y) {
        try {
            coordinates.setYStr(y);
        } catch (Exception e) {
            throw new InvalidYCoordinateException();
        }
    }

    public void setPrice(String price) {
        try {
            ticket.setPriceStr(price);
        } catch (Exception e) {
            throw new InvalidPriceException();
        }
    }

    public void setDiscount(String discount) {
        try {
            ticket.setDiscountStr(discount);
        } catch (Exception e) {
            throw new InvalidDiscountException();
        }
    }

    public void setRefundable(String refundable) {
        try {
            ticket.setRefundableStr(refundable);
        } catch (Exception e) {
            throw new InvalidRefundableException();
        }
    }

    public void setType(String type) {
        try {
            ticket.setTypeStr(type);
        } catch (Exception e) {
            throw new InvalidTypeException();
        }
    }

    public void setWeight(String weight) {
        try {
            person.setWeightStr(weight);
        } catch (Exception e) {
            throw new InvalidRefundableException();
        }
    }

    public void setEyeColor(String eyeColor) {
        try {
            person.setEyeColorStr(eyeColor);
        } catch (Exception e) {
            throw new InvalidRefundableException();
        }
    }

    public void setHairColor(String hairColor) {
        try {
            person.setHairColorStr(hairColor);
        } catch (Exception e) {
            throw new InvalidRefundableException();
        }
    }

    public void setCountry(String country) {
        try {
            person.setNationalityStr(country);
        } catch (Exception e) {
            throw new InvalidRefundableException();
        }
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
