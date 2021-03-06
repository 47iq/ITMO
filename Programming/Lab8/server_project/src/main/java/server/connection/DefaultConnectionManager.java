package server.connection;

import common.*;
import org.apache.logging.log4j.LogManager;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.CommandFactory;
import server.datawork.UsersDataBase;
import server.exceptions.*;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

public class DefaultConnectionManager implements ConnectionManager {

    private final RequestReader requestReader;
    private final CommandFactory commandFactory;
    private final Set<String> ticketCommands;
    private final ObjectFactory serverObjectFactory;
    private final ResponseSender responseSender;
    private final UsersDataBase usersDataBase;
    private final CollectionManager collectionManager;
    private final ExecutorService executionThreads = new ForkJoinPool(5);
    private List<Ticket> tickets = new ArrayList<>();

    public DefaultConnectionManager(CommandFactory commandFactory,
                                    Set<String> ticketCommands, ResponseSender responseSender,
                                    ObjectFactory serverObjectFactory, RequestReader requestReader, UsersDataBase usersData, CollectionManager collectionManager) {
        this.commandFactory = commandFactory;
        this.ticketCommands = ticketCommands;
        this.responseSender = responseSender;
        this.serverObjectFactory = serverObjectFactory;
        this.requestReader = requestReader;
        this.usersDataBase = usersData;
        this.collectionManager = collectionManager;
        monitorUpdates();
    }

    @Override
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
                    LogManager.getLogger().error("Sent error response " + new UnknownException().getMessage());
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
        LogManager.getLogger().info("Got request " + request.toString());
        switch (request.getType()) {
            case EXECUTE -> response = execute(request);
            case ASK_TICKET -> response = sendTicketNeeded(request);
            case REGISTER -> response = register(request);
            case LOGIN -> response = login(request);
            case COLOR -> response = execColor(request);
            case GET_COLLECTION -> response = getCollection();
            default -> {
                LogManager.getLogger().error("Unknown request type got. Sending error answer to the client...");
                throw new UnknownTypeException();
            }
        }
        return response;
    }

    private Response getCollection() {
        try {
            synchronized (this) {
                LogManager.getLogger().info(Thread.currentThread() + " is waiting...");
                this.wait();
                LogManager.getLogger().info(Thread.currentThread() + " has been notified.");
            }
            Response response = serverObjectFactory.getResponse(true, "");
            response.setCollection(tickets);
            return response;
        } catch (InterruptedException ignored) {
            Response response = serverObjectFactory.getResponse(false, "");
            response.setCollection(tickets);
            return response;
        }
    }

    private Response execColor(Request request) {
        if(checkAccess(request))
            try {
                User user = request.getUser();
                String commandName = request.getCommandName();
                Color color;
                Response response;
                Map<String, Color> colorMap;
                switch (commandName) {
                    case "get_colors" -> {
                        colorMap = usersDataBase.getUserColors();
                        response = serverObjectFactory.getResponse(true, "");
                        response.setColorMap(colorMap);
                        System.out.println(colorMap);
                    }
                    case "update_color" -> {
                        color = usersDataBase.getNewColor(user.getLogin());
                        response = serverObjectFactory.getResponse(true, "");
                        response.setColor(color);
                    }
                    default -> throw new RuntimeException();
                }
                return response;
            } catch (Exception e) {
                throw new CommandExecutionException();
            }
        else
            throw new NotLoggedInException();
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
            return commandFactory.executeCommand(commandName, ticket, arg, user, updateData, usersDataBase);
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


    private void monitorUpdates() {
        new Thread(() -> {
            while (true) {
                synchronized (this) {
                    if (!tickets.equals(collectionManager.getTicketList()))
                        setTickets(collectionManager.getTicketList());
                    try {
                        wait(3000);
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        }).start();
    }

    private void setTickets(List<Ticket> tickets) {
        synchronized (this) {
            this.tickets = tickets;
            this.notifyAll();
        }
    }
}
