package main;

import main.ticket.Ticket;

public interface Messenger {
    String getTicketMessage(Ticket ticket);
    String getCommandsMessages();
    String getCollectionMessage(String type, String size, String creationDate);
    String getNameInputInvitationMessage();
    String getXInputInvitationMessage();
    String getYInputInvitationMessage();
    String getDiscountInputInvitationMessage();
    String getPriceInputInvitationMessage();
    String getRefundableInputInvitationMessage();
    String getTicketTypeInputInvitationMessage();
    String getWeightInputInvitationMessage();
    String getEyeColorInputInvitationMessage();
    String getHairColorInputInvitationMessage();
    String getCountryInputInvitationMessage();
}
