package main;

import main.ticket.Ticket;

/**
 * Interface that provides messaging methods
 */

public interface Messenger {

    /**
     * Converter to string from ticket
     * @param ticket ticket we want to convert
     * @return ticket in string format
     */

    String getTicketMessage(Ticket ticket);

    /**
     * Get all commands information
     * @return information about the commands
     */

    String getCommandsMessages();

    /**
     * Get information about {@link Ticket} collection
     * @param type type of the collection
     * @param size size of the collection
     * @param creationDate collection's creation time
     * @return collection information string
     */

    String getCollectionMessage(String type, String size, String creationDate);

    /**
     * Name field invitation message
     * @return invitation message
     */

    String getNameInputInvitationMessage();

    /**
     * X coordinate field invitation message
     * @return invitation message
     */

    String getXInputInvitationMessage();

    /**
     * Y coordinate field invitation message
     * @return invitation message
     */

    String getYInputInvitationMessage();

    /**
     * Discount field invitation message
     * @return invitation message
     */

    String getDiscountInputInvitationMessage();

    /**
     * Price field invitation message
     * @return invitation message
     */

    String getPriceInputInvitationMessage();

    /**
     * Refundable field invitation message
     * @return invitation message
     */

    String getRefundableInputInvitationMessage();

    /**
     * Ticket type field invitation message
     * @return invitation message
     */

    String getTicketTypeInputInvitationMessage();

    /**
     * Weight field invitation message
     * @return invitation message
     */

    String getWeightInputInvitationMessage();

    /**
     * Eye color field invitation message
     * @return invitation message
     */

    String getEyeColorInputInvitationMessage();

    /**
     * Hair color field invitation message
     * @return invitation message
     */

    String getHairColorInputInvitationMessage();

    /**
     * Nationality field invitation message
     * @return invitation message
     */

    String getCountryInputInvitationMessage();
}
