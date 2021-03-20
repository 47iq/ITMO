package client.connection;

import client.Main;
import client.ObjectFactory;
import client.command_manager.CommandFactory;
import client.commands.*;
import client.exceptions.CommunicationException;
import client.exceptions.ConnectionException;
import client.reader.CommandReader;

import common.*;
import org.apache.logging.log4j.LogManager;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Singleton class which parses {@link Command} and executes it
 *
 * @version 1.0
 * @autor 47iq
 */

public class DefaultConnectionManager implements ConnectionManager {

    private static DefaultConnectionManager instance = null;

    private final ObjectFactory ticketFactory;
    private ResponseReader responseReader;
    private RequestSender requestSender;
    private final CommandFactory commandFactory;
    private final InetAddress inetAddress;
    private final int port;
    private User user;

    public DefaultConnectionManager(ObjectFactory ticketFactory, InetAddress address, int port, CommandFactory commandFactory) {
        this.ticketFactory = ticketFactory;
        this.inetAddress = address;
        this.port = port;
        this.commandFactory = commandFactory;
    }

    public static synchronized ConnectionManager getInstance(ObjectFactory ticketFactory, InetAddress address, int port, CommandFactory factory) {
        if (instance == null)
            instance = new DefaultConnectionManager(ticketFactory, address, port, factory);
        return instance;
    }

    public Response executeCommand(String commandName, CommandReader commandReader, String arg) {
        Command command = commandFactory.getCommand(commandName);
        Request commandRequest = ticketFactory.getRequest(RequestType.EXECUTE, commandName);
        Request ticketRequest = ticketFactory.getRequest(RequestType.ASK_TICKET, commandName);
        if (command != null && !(command instanceof ClientServerCommand))
            commandFactory.executeCommand(command, commandReader, arg, this);
        else {
            try {
                if(command != null)
                    commandRequest = manageClientServer((ClientServerCommand) command, commandRequest);
                commandRequest.setUser(user);
                ticketRequest.setUser(user);
                updateConnection();
                requestSender.sendRequest(ticketRequest);
                Response ticketResponse = responseReader.readResponse();
                commandFactory.executeCommand(command, commandReader, arg, this);
                if(command instanceof AuthCommand)
                    commandRequest.setUser(user);
                if (ticketResponse.isSuccessful() && ticketResponse.getMessage().equals("true")) {
                    //commandRequest.setTicket(commandReader.readTicket());
                } else if (!ticketResponse.isSuccessful())
                    Main.getErr().println(ticketResponse.getMessage());

                updateConnection();
                commandRequest.setArg(arg);
                requestSender.sendRequest(commandRequest);
                return responseReader.readResponse();
            } catch (ConnectionException e) {
                //FIXME
                e.printStackTrace();
                return ticketFactory.getResponse(false, "Connection error");
            } catch (Exception e) {
                if(!commandName.equals("shut_down")) {
                    //FIXME
                    e.printStackTrace();
                    System.out.println("here");
                    return ticketFactory.getResponse(false, "Communication error");
                }
            }
        }
        //FIXME
        return ticketFactory.getResponse(false, "Communication error");
    }

    private Request manageClientServer (ClientServerCommand command, Request request) {
        if(command instanceof RegisterCommand)
            request.setType(RequestType.REGISTER);
        if(command instanceof LoginCommand)
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
