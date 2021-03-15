package common;

public interface TicketValidator {
    String castName(String name);

    TicketType castType(String type);

    Boolean castRefundable(String refundable);

    double castDiscount(String discount);

    int castPrice(String price);
}
