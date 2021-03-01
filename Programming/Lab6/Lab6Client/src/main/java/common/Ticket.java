package common;

import java.time.ZonedDateTime;

public interface Ticket extends Comparable<Ticket> {
    double getDiscount();
    Boolean getRefundable();
    void setType(TicketType type);
    int getPrice();
    Person getPerson();
    String getName();
    int getId();
    ZonedDateTime getCreationDate();
    TicketType getType();
    void setName(String name);
    void setCoordinates(Coordinates coordinates);
    void setPrice(int price);
    void setDiscount(double discount);
    void setRefundable(Boolean refundable);
    void setPerson(Person person);
    Coordinates getCoordinates();
    default int compareTo(Ticket ticket) {
        return getName().compareTo(ticket.getName());
    }
    void setNameStr(String name);
    void setPriceStr(String price);
    void setDiscountStr(String discount);
    void setRefundableStr(String refundable);
    void setTypeStr(String type);
}
