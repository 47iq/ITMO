package client.connection;

import client.Client;
import client.ObjectFactory;
import client.commands.ClientServerCommand;
import client.commands.ExitCommand;
import client.commands.ScriptCommand;
import client.messages.Messenger;
import client.reader.CommandReader;

import common.*;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Singleton class which parses {@link Command} and executes it
 *
 * @version 1.0
 * @autor 47iq
 */

public class DefaultRequestFactory implements RequestFactory {

    private static DefaultRequestFactory instance = null;

    private final Map<String, Command> commands;
    private final ObjectFactory ticketFactory;
    private final Messenger messenger;
    private ResponseReader responseReader;
    private RequestSender requestSender;
    protected static Set<File> files = new HashSet<>();
    private final InetAddress inetAddress;
    private final int port;
    private final Client client;

    private DefaultRequestFactory(Map<String, Command> commands, ObjectFactory ticketFactory,
                                  Messenger messenger, InetAddress address, int port, Client client) {
        this.commands = commands;
        this.ticketFactory = ticketFactory;
        this.messenger = messenger;
        this.inetAddress = address;
        this.port = port;
        this.client = client;
    }

    public static synchronized RequestFactory getInstance(Map<String, Command> commands, ObjectFactory ticketFactory,
                                                          Messenger messenger, InetAddress address, int port, Client client) {
        if (instance == null)
            instance = new DefaultRequestFactory(commands, ticketFactory, messenger, address, port, client);
        return instance;
    }

    private Command getCommand(String commandName) {
        return commands.get(commandName);

    }

    public void executeCommand(String commandName, CommandReader commandReader, String arg) throws IOException {
        Command command = getCommand(commandName);
        Request commandRequest = ticketFactory.getRequest(RequestType.EXECUTE, commandName);
        Request ticketRequest = ticketFactory.getRequest(RequestType.ASK_TICKET, commandName);
        if (command instanceof ScriptCommand)
            executeScriptCommand((ScriptCommand) command, commandReader, arg, requestSender);
        else if (command instanceof ExitCommand)
            ((ExitCommand) command).execute(client);
        else {
            try {
                updateConnection();
                requestSender.sendRequest(ticketRequest);
                Response ticketResponse = responseReader.readResponse();
                if (ticketResponse.isSuccessful() && ticketResponse.getMessage().equals("true")) {
                    commandRequest.setTicket(commandReader.readTicket());
                } else if (!ticketResponse.isSuccessful())
                    System.err.println(ticketResponse.getMessage());

                updateConnection();
                commandRequest.setArg(arg);
                requestSender.sendRequest(commandRequest);
                Response response = responseReader.readResponse();
                if (response.isSuccessful() && !response.getMessage().equals("")) {
                    System.out.print(response.getMessage());
                } else if (!response.isSuccessful())
                    System.err.println(response.getMessage());

                if (command instanceof ClientServerCommand)
                    ((ClientServerCommand) command).execute(messenger);
            } catch (Exception e) {
                System.err.print("Error got while communicating to the server: ");
                if (e.getMessage() != null)
                    System.err.println(e.getMessage());
            }
        }
    }

    private void updateConnection() {
        try {
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(inetAddress, port));
            requestSender = ticketFactory.getRequestSender(socketChannel);
            responseReader = ticketFactory.getResponseReader(socketChannel);
        } catch (Exception e) {
            throw new RuntimeException("Server is temporary unavailable.");
        }
    }

    private void executeScriptCommand(ScriptCommand command, CommandReader commandReader, String arg,
                                      RequestSender requestSender) {
        File file = new File(arg);
        if (files.contains(file)) {
            System.err.println("Error. Script recursion has been detected.");
            return;
        }
        files.add(file);
        command.execute(requestSender, commandReader, arg, this, ticketFactory);
        files.remove(file);
    }
}
