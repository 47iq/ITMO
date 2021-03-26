package server.messages;

import common.Ticket;

public interface Messenger {
    String getTicketMessage(Ticket ticket);

    String getCommandsMessages();

    String getCollectionMessage(String type, String size, String creationDate);
}
