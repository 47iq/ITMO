package server.commands;

import common.Response;
import common.Ticket;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;

/**
 * Class of info command
 *
 * @version 1.0
 * @autor 47iq
 */

public class InfoCommand implements MessagingCommand {

    public InfoCommand() {

    }

    @Override
    public Response execute(CollectionManager collectionManager, Ticket ticket, String arg,
                            ObjectFactory factory) {
        String[] info = collectionManager.displayInfo();
        Response response = factory.getResponse(true, "");
        response.setInfo(info);
        return response;
    }

    @Override
    public Response accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
