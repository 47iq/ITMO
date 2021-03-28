package server.commands;

import common.Response;
import common.Ticket;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;

/**
 * Class of max_by_coordinates command
 *
 * @version 1.0
 * @autor 47iq
 */

public class MaxByCoordinatesCommand implements MessagingCommand {


    public MaxByCoordinatesCommand() {
    }

    @Override
    public Response execute(CollectionManager collectionManager, Ticket ticket, String arg,
                            ObjectFactory factory) {
        return factory.getResponse(true, ticket.toString());
    }

    @Override
    public Response accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
