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

    public CommandVisitor() {

    }

    public void configure(Ticket ticket, String arg, CollectionManager collectionManager,
                          ObjectFactory factory, Messenger messenger) {
        curArg = arg;
        curTicket = ticket;
        this.collectionManager = collectionManager;
        this.factory = factory;
        this.messenger = messenger;
    }

    public Response doForSimple(SimpleCommand command) {
        LogManager.getLogger().info("Executing command: {}", command.getClass());
        return command.execute(collectionManager, curArg, factory);
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
        return command.execute(collectionManager, curTicket, curArg, factory);
    }
}
