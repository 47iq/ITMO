package server.commands;

import common.Response;
import common.Ticket;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;

/**
 * Class of remove_greater command
 *
 * @version 1.0
 * @autor 47iq
 */

public class RemoveGreaterCommand implements TicketCommand {

    public RemoveGreaterCommand() {

    }

    @Override
    public Response execute(CollectionManager collectionManager, Ticket ticket, String arg, ObjectFactory factory, String login) {
        if (ticket != null)
            collectionManager.removeGreater(ticket, login);
        return factory.getResponse(true, "");
    }

    @Override
    public Response accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
