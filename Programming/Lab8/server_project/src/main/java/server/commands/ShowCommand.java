package server.commands;

import common.Response;
import common.Ticket;
import server.Main;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;
import server.exceptions.CommandExecutionException;
import server.messages.Messenger;

import java.util.List;

/**
 * Class of show command
 * @autor 47iq
 * @version 1.0
 */

public class ShowCommand implements MessagingCommand {

    public ShowCommand() {

    }

    public Response execute(CollectionManager collectionManager, Ticket ticket, String arg, Messenger messenger,
                            ObjectFactory factory) {
        List<Ticket> ticketList = collectionManager.displayElements();
        StringBuilder message = new StringBuilder();
        for (Ticket ticket1 : ticketList) {
            message.append(messenger.getTicketMessage(ticket1)).append("\n");
        }
        return factory.getResponse(true, message.toString());
    }

    public Response accept(Visitor visitor) {
        return visitor.doForMessaging(this);
    }
}
