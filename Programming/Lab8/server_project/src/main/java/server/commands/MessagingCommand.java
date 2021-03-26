package server.commands;

import common.Response;
import common.Ticket;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.messages.Messenger;

/**
 * Interface of command that uses messenger
 */

public interface MessagingCommand extends Command {

    /**
     * Method that executes command
     *
     * @param collectionManager manager of the collection
     * @param ticket            ticket
     * @param arg               argument of the command
     * @param messenger         messenger
     */
    Response execute(CollectionManager collectionManager, Ticket ticket, String arg,
                     Messenger messenger, ObjectFactory serverObjectFactory);
}
