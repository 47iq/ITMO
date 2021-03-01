package server.commands;

import common.Response;
import common.Ticket;
import server.collection.CollectionManager;
import server.ObjectFactory;

public interface TicketCommand extends Command{
    Response execute(CollectionManager collectionManager, Ticket ticket, String arg, ObjectFactory factory);
}
