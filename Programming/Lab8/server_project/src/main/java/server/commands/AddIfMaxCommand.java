package server.commands;

import common.Response;
import common.Ticket;
import server.Main;
import server.exceptions.CommandExecutionException;
import server.exceptions.InvalidTicketException;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;

/**
 * Class of add_if_max command
 * @autor 47iq
 * @version 1.0
 */

public class AddIfMaxCommand implements TicketCommand {

    public AddIfMaxCommand() {
    }

    public Response execute(CollectionManager collectionManager, Ticket ticket, String arg, ObjectFactory factory, String login) {
        if (ticket == null)
            throw new InvalidTicketException();
        collectionManager.addIfMax(ticket);
        return factory.getResponse(true, "");
    }

    public Response accept(Visitor visitor) {
        return visitor.doForTicket(this);
    }
}
