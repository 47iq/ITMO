package server.commands;

import common.Response;
import common.Ticket;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;
import server.messages.Messenger;

/**
 * Class of help command
 * @autor 47iq
 * @version 1.0
 */

public class HelpCommand implements MessagingCommand {

    public HelpCommand() {
    }

    public Response execute(CollectionManager collectionManager, Ticket ticket, String arg, Messenger messenger,
                            ObjectFactory factory) {
        try {
            return factory.getResponse(true, messenger.getCommandsMessages());
        } catch (Exception e) {
            return factory.getResponse(false, "Error got while displaying commands.");
        }
    }

    public Response accept(Visitor visitor) {
        return visitor.doForMessaging(this);
    }
}
