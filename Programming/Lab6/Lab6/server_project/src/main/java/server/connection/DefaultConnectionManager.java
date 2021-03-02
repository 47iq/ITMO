package server.connection;

import common.Request;
import common.RequestType;
import common.Response;
import common.Ticket;
import org.apache.logging.log4j.LogManager;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.CommandFactory;

import java.io.IOException;
import java.io.UTFDataFormatException;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.util.Set;

public class DefaultConnectionManager implements ConnectionManager {

    private final RequestReader requestReader;
    private final CollectionManager collectionManager;
    private final CommandFactory commandFactory;
    private final Set<String> ticketCommands;
    private final ObjectFactory serverObjectFactory;
    private final ResponseSender responseSender;

    public DefaultConnectionManager(CollectionManager collectionManager, CommandFactory commandFactory,
                                    Set<String> ticketCommands, ResponseSender responseSender,
                                    ObjectFactory serverObjectFactory, RequestReader requestReader) {
        this.collectionManager = collectionManager;
        this.commandFactory = commandFactory;
        this.ticketCommands = ticketCommands;
        this.responseSender = responseSender;
        this.serverObjectFactory = serverObjectFactory;
        this.requestReader = requestReader;
    }

    public void manageConnection(Selector selector) {
        Request request;
        Response response;
        try {
            try {
                ByteBuffer buffer = ByteBuffer.allocate(4096);
                request = requestReader.readRequest(selector, buffer);
                commandFactory.setMessenger(request.getLocale());
                response = getResponse(request);
                if(response == null)
                    return;
                buffer.clear();
                responseSender.sendResponse(response, selector);
            } catch (UTFDataFormatException e) {
                LogManager.getLogger().error("Broken packet has been received. Sending error answer to the client...");
                responseSender.sendResponse(serverObjectFactory.getResponse(false, "Error. Damaged package has been sent by client."), selector);
            } catch (Exception e) {
                LogManager.getLogger().error(e.getClass() + " got. Sending error answer to the client...");
                if(e.getMessage() != null)
                    responseSender.sendResponse(serverObjectFactory.getResponse(false, e.getMessage()), selector);
                else
                    responseSender.sendResponse(serverObjectFactory.getResponse(false, "Unknown error got. Error type: " + e.getClass()), selector);
            }
        } catch (IOException e) {
            LogManager.getLogger().error("Terminal error {} got.", e.getClass());
        }
    }

    private Response getResponse(Request request) {
        Response response;
        if (request.getType().equals(RequestType.EXECUTE)) {
            response = execute(request);
        } else if (request.getType().equals(RequestType.ASK_TICKET)) {
            response = sendTicketNeeded(request);
        } else {
            LogManager.getLogger().error("Unknown request type got. Sending error answer to the client...");
            response = serverObjectFactory.getResponse(false, "Error. Unknown request type. Update your app client to the latest version.");
        }
        return response;
    }

    private Response execute(Request request) {
        try {
            String commandName = request.getCommandName();
            Ticket ticket = request.getTicket();
            String arg = request.getArg();
            String password = request.getPassword();
            return commandFactory.executeCommand(commandName, ticket, arg, password);
        } catch (Exception e) {
            LogManager.getLogger().error("{} got while executing command.", e.getClass());
            return serverObjectFactory.getResponse(false, "Unknown error got while executing the command.");
        }
    }

    private Response sendTicketNeeded(Request request) {
        try {
            if (ticketCommands.contains(request.getCommandName()))
                return serverObjectFactory.getResponse(true, "true");
            else
                return serverObjectFactory.getResponse(true, "false");
        } catch (Exception e) {
            LogManager.getLogger().error("{} got while getting ticket info about the program.", e.getClass());
            return serverObjectFactory.getResponse(false, "Error. Can't get info about command.");
        }
    }
}
