package client.messages;

/**
 * Interface that provides messaging methods
 */

public interface Messenger {

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

    String getCommands();
}
