package server.commands;

import common.Response;
import common.Ticket;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;
import server.messages.Messenger;

/**
 * Class of show command
 *
 * @version 1.0
 * @autor 47iq
 */

public class ShowCommand implements MessagingCommand {

    public ShowCommand() {

    }

    public Response execute(CollectionManager collectionManager, Ticket ticket, String arg, Messenger messenger,
                            ObjectFactory factory) {
        Response response = factory.getResponse(true, "");
        response.setCollection(collectionManager.getTicketList());
        return response;
    }

    public Response accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
