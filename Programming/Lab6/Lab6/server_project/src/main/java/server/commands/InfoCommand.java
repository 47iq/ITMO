package server.commands;

import common.Response;
import common.Ticket;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;
import server.messages.Messenger;

import java.util.List;

/**
 * Class of info command
 * @autor 47iq
 * @version 1.0
 */

public class InfoCommand implements MessagingCommand {

    public InfoCommand() {

    }

    public Response execute(CollectionManager collectionManager, Ticket ticket, String arg, Messenger messenger,
                            ObjectFactory factory) {
        try {
            List<String> info = collectionManager.displayInfo();
            return factory.getResponse(true, messenger.getCollectionMessage(info.get(0), info.get(1), info.get(2)));
        } catch (Exception e) {
            return factory.getResponse(false, "Error got while displaying the info");
        }
    }

    public Response accept(Visitor visitor) {
        return visitor.doForMessaging(this);
    }
}
