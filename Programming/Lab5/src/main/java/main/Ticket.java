package main;

import org.json.simple.JSONObject;

import java.time.ZonedDateTime;

public interface Ticket extends TicketFieldCaster, Comparable<Ticket>{
    double getDiscount();
    int getId();
    Boolean getRefundable();
    void configureId(int id);
    void setType(TicketType type);
    int getPrice();
    Person getPerson();
    ZonedDateTime getCreationDate();
    String getName();
    TicketType getType();
    void setName(String name);
    void setCoordinates(Coordinates coordinates);
    void setPrice(int price);
    void setDiscount(double discount);
    void setRefundable(Boolean refundable);
    void setPerson(Person person);
    JSONObject toJSON();
    Coordinates getCoordinates();
    default int compareTo(Ticket ticket) {
        int priceDiff = getPrice() - ticket.getPrice();
        if(priceDiff != 0)
            return priceDiff;
        else
            return getId() - ticket.getId();
    }
}
