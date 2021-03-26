package server.commands;

import common.Response;
import common.Ticket;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;
import server.messages.Messenger;

/**
 * Class of help command
 *
 * @version 1.0
 * @autor 47iq
 */

public class HelpCommand implements MessagingCommand {

    public HelpCommand() {
    }

    public Response execute(CollectionManager collectionManager, Ticket ticket, String arg, Messenger messenger,
                            ObjectFactory factory) {
        return factory.getResponse(true, messenger.getCommandsMessages());
    }

    public Response accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
