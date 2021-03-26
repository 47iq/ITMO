package server.command_manager;

import common.Response;
import common.Ticket;
import common.UpdateData;
import org.apache.logging.log4j.LogManager;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.commands.*;
import server.messages.MessagesENG;
import server.messages.Messenger;

public class CommandVisitor implements Visitor {

    private String curArg;
    private Ticket curTicket;
    private CollectionManager collectionManager;
    private ObjectFactory factory;
    private String login;
    private UpdateData updateData;

    public CommandVisitor() {

    }

    public CommandVisitor(String curArg, Ticket curTicket, CollectionManager collectionManager, ObjectFactory factory,
                          String login, UpdateData updateData) {
        this.curArg = curArg;
        this.curTicket = curTicket;
        this.collectionManager = collectionManager;
        this.factory = factory;
        this.login = login;
        this.updateData = updateData;
    }

    public Response visit(SimpleCommand command) {
        LogManager.getLogger().info("Executing command: {}", command.getClass());
        return command.execute(collectionManager, curArg, factory, login);
    }

    public Response visit(MessagingCommand command) {
        LogManager.getLogger().info("Executing command: {}", command.getClass());
        Messenger messenger = new MessagesENG();
        return command.execute(collectionManager, curTicket, curArg, messenger, factory);
    }

    public Response visit(ServerCommand command) {
        LogManager.getLogger().info("Executing admin command: {}", command.getClass());
        command.execute(collectionManager);
        return null;
    }

    public Response visit(TicketCommand command) {
        LogManager.getLogger().info("Executing command: {}", command.getClass());
        return command.execute(collectionManager, curTicket, curArg, factory, login);
    }

    @Override
    public Response visit(UpdatingCommand command) {
        LogManager.getLogger().info("Executing command: {}", command.getClass());
        return command.execute(collectionManager, curTicket, curArg, updateData, factory, login);
    }
}
