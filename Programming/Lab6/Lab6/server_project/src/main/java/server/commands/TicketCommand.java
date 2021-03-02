package server.commands;

import common.Response;
import common.Ticket;
import server.ObjectFactory;
import server.collection.CollectionManager;

public interface TicketCommand extends Command{
    Response execute(CollectionManager collectionManager, Ticket ticket, String arg, ObjectFactory factory);
}
