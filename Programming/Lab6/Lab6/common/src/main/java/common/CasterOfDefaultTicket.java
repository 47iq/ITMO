package common;

import exceptions.InvalidDiscountException;
import exceptions.InvalidNameException;
import exceptions.InvalidPriceException;
import exceptions.InvalidRefundableException;

/**
 * Interface that provides methods which validate and cast Default {@link Ticket}'s fields
 */

public interface CasterOfDefaultTicket {
    /**
     * Parses and validates {@link Ticket#getRefundable()} from {@link String}
     * @param inputStr refundable in string format
     * @return refundable {@link Boolean}
     */


    default Boolean castRefundable(String inputStr){
        if(inputStr == null || inputStr.equals(""))
            return null;
        if(inputStr.equals("true"))
            return true;
        if(inputStr.equals("false"))
            return false;
        throw
            new InvalidRefundableException();
    }

    /**
     * Parses and validates {@link Ticket#getName()} from {@link String}
     * @param inputStr name in string format
     * @return name {@link String}
     */

    default String castName(String inputStr) {
        if(nameValid(inputStr))
            return inputStr;
        else
            throw new InvalidNameException();
    }


    /**
     * A price validator
     * @param price int
     * @return true if price is valid, false if not
     */

    default boolean priceValid(int price) {
        return price > 0;
    }

    /**
     * A name validator
     * @param name manager.ticket's name
     * @return true if name is valid, false if not
     */

    default boolean nameValid(String name) {
        return name != null && !name.equals("");
    }

    /**
     * A discount validator
     * @param discount manager.ticket's discount
     * @return true if discount is valid, false if not
     */

    default boolean discountValid(double discount) {
        return discount > 0 && discount <= 100;
    }

    /**
     * Method which casts String type into TicketType
     * @param typeStr Type in the String format
     * @return Type in TicketType format
     */

    default TicketType castType(String typeStr) {
        switch (typeStr.toLowerCase()) {
            case "vip": return TicketType.VIP;
            case "cheap": return TicketType.CHEAP;
            case "usual": return TicketType.USUAL;
            case "":
            case "null":
                return null;
            default: throw new exceptions.InvalidTypeException();
        }
    }

    /**
     * Method which casts String price to int
     * @param price price in string format
     * @return manager.ticket price
     */

    default int castPrice(String price){
        int priceInt = Integer.parseInt(price);
        if(!priceValid(priceInt))
            throw new InvalidPriceException();
        else
            return priceInt;
    }

    /**
     * Method which casts {@link String} discount to double
     * @param discount discount in string format
     * @return discount manager.ticket discount
     */

    default double castDiscount(String discount){
        double discountDouble = Double.parseDouble(discount);
        if(!discountValid(discountDouble)) {
            throw new InvalidDiscountException();
        }
        else
            return discountDouble;
    }
}
