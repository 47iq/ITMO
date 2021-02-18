package main.ticket;

import main.CasterOfDefaultTicket;
import main.ClientObjectFactory;

public class ClientTemplateTicket extends DefaultTicket implements CasterOfDefaultTicket {

    public ClientTemplateTicket() {

    }

    public ClientTemplateTicket(String name, double x, Integer y, int price, double discount, Boolean refundable, TicketType type, Long weight,
                               EyesColor eyesColor, HairColor hairColor, Country country, ClientObjectFactory ticketFactory) {
        setName(name);
        setCoordinates(ticketFactory.getCoordinates(x, y));
        setPrice(price);
        setDiscount(discount);
        setRefundable(refundable);
        setType(type);
        setPerson(ticketFactory.getPerson(weight, eyesColor, hairColor, country));
    }
}
