package server.commands;

import common.Response;
import common.Ticket;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;
import server.exceptions.InvalidTicketException;

/**
 * Class of add command
 *
 * @version 1.0
 * @autor 47iq
 */

public class AddCommand implements TicketCommand {

    /**
     * Constructor of the add command
     */

    public AddCommand() {

    }

    @Override
    public Response execute(CollectionManager collectionManager, Ticket ticket, String arg, ObjectFactory factory, String login) {
        if (ticket == null)
            throw new InvalidTicketException();
        collectionManager.convertAddTicket(ticket);
        return factory.getResponse(true, "");
    }

    @Override
    public Response accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
