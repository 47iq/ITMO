package common;

import server.exceptions.InvalidDiscountException;
import server.exceptions.InvalidNameException;
import server.exceptions.InvalidPriceException;
import server.exceptions.InvalidRefundableException;

public class DefaultTicketValidator implements TicketValidator {

    /**
     * Parses and validates {@link Ticket#getRefundable()} from {@link String}
     *
     * @param inputStr refundable in string format
     * @return refundable {@link Boolean}
     */

    public Boolean castRefundable(String inputStr) {
        if (inputStr == null || inputStr.equals(""))
            return null;
        if (inputStr.equals("true"))
            return true;
        if (inputStr.equals("false"))
            return false;
        throw
                new InvalidRefundableException();
    }

    /**
     * Parses and validates {@link Ticket#getName()} from {@link String}
     *
     * @param inputStr name in string format
     * @return name {@link String}
     */

    public String castName(String inputStr) {
        if (nameValid(inputStr))
            return inputStr;
        else
            throw new InvalidNameException();
    }


    /**
     * A price validator
     *
     * @param price int
     * @return true if price is valid, false if not
     */

    boolean priceValid(int price) {
        return price > 0;
    }

    /**
     * A name validator
     *
     * @param name manager.ticket's name
     * @return true if name is valid, false if not
     */

    boolean nameValid(String name) {
        return name != null && !name.equals("");
    }

    /**
     * A discount validator
     *
     * @param discount manager.ticket's discount
     * @return true if discount is valid, false if not
     */

    boolean discountValid(double discount) {
        return discount > 0 && discount <= 100;
    }

    /**
     * Method which casts String type into TicketType
     *
     * @param typeStr Type in the String format
     * @return Type in TicketType format
     */

    public TicketType castType(String typeStr) {
        if (typeStr == null)
            return null;
        switch (typeStr.toLowerCase()) {
            case "vip":
                return TicketType.VIP;
            case "cheap":
                return TicketType.CHEAP;
            case "usual":
                return TicketType.USUAL;
            case "":
            case "null":
                return null;
            default:
                throw new server.exceptions.InvalidTypeException();
        }
    }

    /**
     * Method which casts String price to int
     *
     * @param price price in string format
     * @return manager.ticket price
     */

    public int castPrice(String price) {
        int priceInt = Integer.parseInt(price);
        if (!priceValid(priceInt))
            throw new InvalidPriceException();
        else
            return priceInt;
    }

    /**
     * Method which casts {@link String} discount to double
     *
     * @param discount discount in string format
     * @return discount manager.ticket discount
     */

    public double castDiscount(String discount) {
        double discountDouble = Double.parseDouble(discount);
        if (!discountValid(discountDouble)) {
            throw new InvalidDiscountException();
        } else
            return discountDouble;
    }
}
