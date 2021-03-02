package server.command_manager;

import common.Response;
import common.Ticket;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.commands.MessagingCommand;
import server.commands.ServerCommand;
import server.commands.SimpleCommand;
import server.commands.TicketCommand;
import server.messages.Messenger;

public interface Visitor {
    Response doForSimple(SimpleCommand command);
    Response doForMessaging(MessagingCommand command);
    Response doForServer(ServerCommand command);
    Response doForTicket(TicketCommand command);
    void configure(Ticket ticket, String arg, CollectionManager collectionManager, ObjectFactory factory, Messenger messenger);
}
