package client.ticket;

import common.Ticket;

public interface TicketBuilder {

    void reset();

    Ticket getResult();

    void setName(String text);

    void setX(String text);

    void setY(String text);

    void setPrice(String text);

    void setDiscount(String text);

    void setRefundable(String text);

    void setType(String value);

    void setWeight(String text);

    void setEyeColor(String value);

    void setHairColor(String value);

    void setCountry(String value);
}
