package server.commands;

import common.Response;
import common.Ticket;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;
import server.messages.Messenger;

/**
 * Class of info command
 *
 * @version 1.0
 * @autor 47iq
 */

public class InfoCommand implements MessagingCommand {

    public InfoCommand() {

    }

    public Response execute(CollectionManager collectionManager, Ticket ticket, String arg, Messenger messenger,
                            ObjectFactory factory) {
        String[] info = collectionManager.displayInfo();
        Response response = factory.getResponse(true, "");
        response.setInfo(info);
        return response;
    }

    public Response accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
