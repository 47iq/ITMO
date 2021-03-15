package server.command_manager;

import common.Response;
import common.Ticket;
import org.apache.logging.log4j.LogManager;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.commands.MessagingCommand;
import server.commands.ServerCommand;
import server.commands.SimpleCommand;
import server.commands.TicketCommand;
import server.messages.Messenger;

public class CommandVisitor implements Visitor{

    private String curArg;
    private Ticket curTicket;
    private CollectionManager collectionManager;
    private ObjectFactory factory;
    private Messenger messenger;
    private String login;

    public CommandVisitor() {

    }

    public CommandVisitor(String curArg, Ticket curTicket, CollectionManager collectionManager, ObjectFactory factory,
                          Messenger messenger, String login) {
        this.curArg = curArg;
        this.curTicket = curTicket;
        this.collectionManager = collectionManager;
        this.factory = factory;
        this.messenger = messenger;
        this.login = login;
    }

    public Response doForSimple(SimpleCommand command) {
        LogManager.getLogger().info("Executing command: {}", command.getClass());
        return command.execute(collectionManager, curArg, factory, login);
    }

    public Response doForMessaging(MessagingCommand command) {
        LogManager.getLogger().info("Executing command: {}", command.getClass());
        return command.execute(collectionManager, curTicket, curArg, messenger, factory);
    }

    public Response doForServer(ServerCommand command) {
        LogManager.getLogger().info("Executing admin command: {}", command.getClass());
        command.execute(collectionManager);
        return null;
    }

    public Response doForTicket(TicketCommand command) {
        LogManager.getLogger().info("Executing command: {}", command.getClass());
        return command.execute(collectionManager, curTicket, curArg, factory, login);
    }
}
