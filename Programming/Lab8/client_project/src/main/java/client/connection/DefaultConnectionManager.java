package client.connection;

import client.ObjectFactory;
import client.command_manager.CommandFactory;
import client.commands.*;
import client.exceptions.ConnectionException;
import client.reader.CommandReader;
import common.*;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Singleton class which parses {@link Command} and executes it
 *
 * @version 1.0
 * @autor 47iq
 */

public class DefaultConnectionManager implements ConnectionManager {

    private final ObjectFactory ticketFactory;
    private ResponseReader responseReader;
    private RequestSender requestSender;
    private final CommandFactory commandFactory;
    private final InetAddress inetAddress;
    private final int port;
    private User user;
    private final Map<String, MessagingCommand> messagingCommands;

    public DefaultConnectionManager(ObjectFactory ticketFactory, InetAddress address, int port, CommandFactory commandFactory,
                                    Map<String, MessagingCommand> messagingCommands) {
        this.ticketFactory = ticketFactory;
        this.inetAddress = address;
        this.port = port;
        this.commandFactory = commandFactory;
        this.messagingCommands = messagingCommands;
    }



    public Collection<Ticket> getCollection() throws InterruptedException, ExecutionException {
        /*ExecutorService executor = Executors.newFixedThreadPool(1);
        FutureTask<List<Ticket>> task = new FutureTask<>(() -> {*/
            try {
                Request request = ticketFactory.getRequest(RequestType.GET_COLLECTION, "show");
                SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(inetAddress, port));
                RequestSender sender = ticketFactory.getRequestSender(socketChannel);
                ResponseReader reader = ticketFactory.getResponseReader(socketChannel);
                sender.sendRequest(request);
                Response response = reader.readResponse();
                return response.getCollection();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        /*});
        executor.execute(task);
        while (!task.isDone())
            task.wait();
        return task.get();*/
    }


    public Response executeCommand(String commandName, CommandReader commandReader, String arg) {
        Command command = commandFactory.getCommand(commandName);
        Request commandRequest = ticketFactory.getRequest(RequestType.EXECUTE, commandName);
        Request ticketRequest = ticketFactory.getRequest(RequestType.ASK_TICKET, commandName);
        if (command != null && !(command instanceof ClientServerCommand))
            return commandFactory.executeCommand(command, commandReader, arg, this);
        else {
            try {
                if (command != null)
                    commandRequest = manageClientServer((ClientServerCommand) command, commandRequest);
                commandRequest.setUser(user);
                commandRequest.setUpdateData(commandReader.getUpdateData());
                ticketRequest.setUser(user);
                updateConnection();
                requestSender.sendRequest(ticketRequest);
                Response ticketResponse = responseReader.readResponse();
                commandFactory.executeCommand(command, commandReader, arg, this);
                if (ticketResponse.isSuccessful() && ticketResponse.getMessage().equals("true")) {
                    commandRequest.setTicket(commandReader.readTicket());
                } else if (!ticketResponse.isSuccessful())
                    return ticketResponse;
                if (command instanceof AuthCommand)
                    commandRequest.setUser(user);
                updateConnection();
                commandRequest.setArg(arg);
                requestSender.sendRequest(commandRequest);
                Response response = responseReader.readResponse();
                return decorateResponse(response, commandName);
            } catch (ConnectionException e) {
                return ticketFactory.getResponse(false, "ERR_CONNECTION");
            } catch (Exception e) {
                if (!commandName.equals("shut_down")) {
                    return ticketFactory.getResponse(false, "ERR_COMMUNICATION");
                }
            }
        }
        return ticketFactory.getResponse(false, "ERR_COMMUNICATION");
    }

    private Response decorateResponse(Response response, String command) {
        if (response.isSuccessful() && messagingCommands.get(command) != null) {
            messagingCommands.get(command).execute(response);
        }
        return response;
    }

    private Request manageClientServer(ClientServerCommand command, Request request) {
        if (command instanceof RegisterCommand)
            request.setType(RequestType.REGISTER);
        if (command instanceof LoginCommand)
            request.setType(RequestType.LOGIN);
        return request;
    }

    private void updateConnection() {
        try {
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(inetAddress, port));
            requestSender = ticketFactory.getRequestSender(socketChannel);
            responseReader = ticketFactory.getResponseReader(socketChannel);
        } catch (Exception e) {
            throw new ConnectionException();
        }
    }

    public void setUser(User user) {
        this.user = user;
    }
}
