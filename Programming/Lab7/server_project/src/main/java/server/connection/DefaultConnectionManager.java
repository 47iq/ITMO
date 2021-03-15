package server.connection;

import common.Request;
import common.RequestType;
import common.Response;
import common.Ticket;
import org.apache.logging.log4j.LogManager;
import server.ObjectFactory;
import server.command_manager.CommandFactory;
import server.datawork.UsersDataBase;
import server.exceptions.*;

import java.net.Socket;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.*;

public class DefaultConnectionManager implements ConnectionManager {

    private final RequestReader requestReader;
    private final CommandFactory commandFactory;
    private final Set<String> ticketCommands;
    private final ObjectFactory serverObjectFactory;
    private final ResponseSender responseSender;
    private final UsersDataBase usersDataBase;
    private final ExecutorService executionThreads = new ForkJoinPool(5);

    public DefaultConnectionManager(CommandFactory commandFactory,
                                    Set<String> ticketCommands, ResponseSender responseSender,
                                    ObjectFactory serverObjectFactory, RequestReader requestReader, UsersDataBase usersData) {
        this.commandFactory = commandFactory;
        this.ticketCommands = ticketCommands;
        this.responseSender = responseSender;
        this.serverObjectFactory = serverObjectFactory;
        this.requestReader = requestReader;
        this.usersDataBase = usersData;
    }

    public void readRequest(Socket client) {
        try {
            Request request = requestReader.readRequest(client);
            proceedRequest(request, client);
        } catch (Exception e) {
            LogManager.getLogger().error("Can't execute request; error: " + e.getClass());
        }

    }

    private void proceedRequest(Request request, Socket client) {
        Thread executionThread = new Thread(() -> {
            Locale locale = request.getLocale();
            Response response = getResponse(request, locale);
            if (response != null)
                sendResponse(response, client);
        });
        executionThreads.submit(executionThread);
    }

    private void sendResponse(Response response, Socket client) {
        Thread outputThread = new Thread(() -> {
            try {
                responseSender.sendResponse(response, client);
            } catch (Exception e) {
                LogManager.getLogger().error("Error. Can't send the request");
            }
        });
        outputThread.start();
    }

    private Response getResponse(Request request, Locale locale) {
        Response response;
        ServerExceptionMessenger errMessenger = serverObjectFactory.getLocalErrMessenger(locale);
        if (request.getType().equals(RequestType.EXECUTE)) {
            response = execute(request, locale);
        } else if (request.getType().equals(RequestType.ASK_TICKET)) {
            response = sendTicketNeeded(request);
        } else  if(request.getType().equals(RequestType.REGISTER)) {
            response = register(request, errMessenger);
        } else  if(request.getType().equals(RequestType.LOGIN)) {
            response = login(request, errMessenger);
        } else {
            LogManager.getLogger().error("Unknown request type got. Sending error answer to the client...");
            response = serverObjectFactory.getResponse(false, new UnknownTypeException().accept(errMessenger));
        }
        return response;
    }

    private Response execute(Request request, Locale locale) {
        String user;
        ServerExceptionMessenger errMessenger = serverObjectFactory.getLocalErrMessenger(locale);
        try {
            String commandName = request.getCommandName();
            Ticket ticket = request.getTicket();
            String arg = request.getArg();
            if(checkAccess(request)) {
                user = request.getUser().getLogin();
                if(ticket != null)
                    ticket.setOwner(user);
                return commandFactory.executeCommand(commandName, ticket, arg, user, locale);
            } else
                throw new NotLoggedInException();
        } catch (NotLoggedInException e) {
            LogManager.getLogger().error("Unknown user tried to execute command.");
            return serverObjectFactory.getResponse(false, new NotLoggedInException().accept(errMessenger));
        } catch (Exception e) {
            LogManager.getLogger().error("{} got while executing command.", e.getClass());
            return serverObjectFactory.getResponse(false, new UnknownException().accept(errMessenger));
        }
    }

    private Response register(Request request, ServerExceptionMessenger errMessenger) {
        try {
            if(usersDataBase.isPresent(request.getUser().getLogin()))
                return serverObjectFactory.getResponse(false, new UserExistsException().accept(errMessenger));
            usersDataBase.add(request.getUser());
            return serverObjectFactory.getResponse(true, "");
        } catch (Exception e) {
            return serverObjectFactory.getResponse(false, new RegistrationException().accept(errMessenger));
        }
    }

    private Response login(Request request, ServerExceptionMessenger errMessenger) {
        if(checkAccess(request)) {
            LogManager.getLogger().info("User: " + request.getUser() + " has logged in.");
            return serverObjectFactory.getResponse(true, "");
        } else
            return serverObjectFactory.getResponse(false, new LoginException().accept(errMessenger));
    }

    private Response sendTicketNeeded(Request request) {
        if (ticketCommands.contains(request.getCommandName()))
            return serverObjectFactory.getResponse(true, "true");
        else
            return serverObjectFactory.getResponse(true, "false");
    }

    private boolean checkAccess(Request request) {
        return request.getUser() != null && request.getUser().getPassword() != null && usersDataBase.isValid(request.getUser());
    }
}
