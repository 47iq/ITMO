package server.command_manager;

import common.Response;
import common.Ticket;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.commands.*;
import server.messages.Messenger;

public interface Visitor {
    Response visit(TicketCommand ticketCommand);
    Response visit(SimpleCommand simpleCommand);
    Response visit(MessagingCommand messagingCommand);
    Response visit(ServerCommand serverCommand);
    Response visit(UpdatingCommand serverCommand);
}
