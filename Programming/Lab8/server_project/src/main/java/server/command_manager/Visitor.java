package server.command_manager;

import common.Response;
import server.commands.*;

public interface Visitor {
    Response visit(TicketCommand ticketCommand);

    Response visit(SimpleCommand simpleCommand);

    Response visit(MessagingCommand messagingCommand);

    Response visit(ServerCommand serverCommand);

    Response visit(UpdatingCommand serverCommand);
}
