package server.connection;

import common.Request;
import common.Response;
import common.Ticket;
import common.UpdateData;
import org.apache.logging.log4j.LogManager;
import server.ObjectFactory;
import server.command_manager.CommandFactory;
import server.datawork.UsersDataBase;
import server.exceptions.*;

import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

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
            try {
                Response response = getResponse(request);
                if (response != null)
                    sendResponse(response, client);
            } catch (Exception e) {
                if (e instanceof CommonException) {
                    LogManager.getLogger().info("Sent error response " + e.getMessage());
                    sendResponse(serverObjectFactory.getResponse(false, e.getMessage()), client);
                } else {
                    LogManager.getLogger().info("Sent error response " + new UnknownException().getMessage());
                    sendResponse(serverObjectFactory.getResponse(false, new UnknownException().getMessage()), client);
                }
            }
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

    private Response getResponse(Request request) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Response response;
        switch (request.getType()) {
            case EXECUTE -> response = execute(request);
            case ASK_TICKET -> response = sendTicketNeeded(request);
            case REGISTER -> response = register(request);
            case LOGIN -> response = login(request);
            default -> {
                LogManager.getLogger().error("Unknown request type got. Sending error answer to the client...");
                throw new UnknownTypeException();
            }
        }
        return response;
    }

    private Response execute(Request request) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String user;
        String commandName = request.getCommandName();
        Ticket ticket = request.getTicket();
        String arg = request.getArg();
        UpdateData updateData = request.getUpdateData();
        if (checkAccess(request)) {
            user = request.getUser().getLogin();
            if (ticket != null)
                ticket.setOwner(user);
            return commandFactory.executeCommand(commandName, ticket, arg, user, updateData);
        } else
            throw new NotLoggedInException();
    }

    private Response register(Request request) {
        if (usersDataBase.isPresent(request.getUser().getLogin()))
            throw new UserExistsException();
        try {
            usersDataBase.add(request.getUser());
            return serverObjectFactory.getResponse(true, "");
        } catch (Exception e) {
            throw new RegistrationException();
        }
    }

    private Response login(Request request) {
        if (checkAccess(request)) {
            LogManager.getLogger().info("User: " + request.getUser() + " has logged in.");
            return serverObjectFactory.getResponse(true, "");
        } else
            throw new LoginException();
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
