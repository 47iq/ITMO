package server.commands;

import common.Response;
import common.Ticket;
import server.Main;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;
import server.exceptions.CommandExecutionException;

/**
 * Class of remove_greater command
 * @autor 47iq
 * @version 1.0
 */

public class RemoveGreaterCommand implements TicketCommand {

    public RemoveGreaterCommand() {

    }

    public Response execute(CollectionManager collectionManager, Ticket ticket, String arg, ObjectFactory factory, String login) {
        if (ticket != null)
            collectionManager.removeGreater(ticket, login);
        return factory.getResponse(true, "");
    }

    public Response accept(Visitor visitor) {
        return visitor.doForTicket(this);
    }
}
