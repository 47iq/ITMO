package server.commands;

import common.Response;
import common.Ticket;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;

/**
 * Class of help command
 *
 * @version 1.0
 * @autor 47iq
 */

public class HelpCommand implements MessagingCommand {

    public HelpCommand() {
    }

    @Override
    public Response execute(CollectionManager collectionManager, Ticket ticket, String arg,
                            ObjectFactory factory) {
        return factory.getResponse(true, "");
    }

    @Override
    public Response accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
