package server.commands;

import server.command_manager.Visitor;
import common.Response;
import common.Ticket;
import server.collection.CollectionManager;
import server.messages.Messenger;
import server.ObjectFactory;

/**
 * Class of max_by_coordinates command
 * @autor 47iq
 * @version 1.0
 */

public class MaxByCoordinatesCommand implements MessagingCommand {


    public MaxByCoordinatesCommand() {
    }

    public Response execute(CollectionManager collectionManager, Ticket ticket, String arg, Messenger messenger,
                            ObjectFactory factory) {
        try{
            return factory.getResponse(true, messenger.getTicketMessage(collectionManager.maxByCoordinates()));
        } catch (Exception e) {
            return factory.getResponse(false, "Error got while displaying the max by coordinates element");
        }
    }

    public Response accept(Visitor visitor) {
        return visitor.doForMessaging(this);
    }
}
