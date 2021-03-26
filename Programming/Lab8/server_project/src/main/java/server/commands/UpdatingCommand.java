package server.commands;

import common.Response;
import common.Ticket;
import common.UpdateData;
import server.ObjectFactory;
import server.collection.CollectionManager;

public interface UpdatingCommand extends Command {
    Response execute(CollectionManager collectionManager, Ticket ticket, String arg, UpdateData data, ObjectFactory factory, String login);
}
