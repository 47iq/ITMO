package client;

import client.commands.ClientHelpCommand;
import client.commands.DefaultExitCommand;
import client.commands.ExecuteScriptCommand;
import client.connection.DefaultRequestFactory;
import client.connection.RequestFactory;
import client.messages.Messenger;
import client.reader.CommandReader;
import client.reader.ConsoleCommandReader;
import common.Command;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Class which starts the program
 * @autor 47iq
 * @version 1.0
 */

public class Main {

    /**
     * The entry point of the program
     * @param args String[] args
     */

    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName(args[0]);
            int port = Integer.parseInt(args[1]);
            ObjectFactory objectFactory = new ClientObjectFactory(new Locale("ru", "RU"));
            Map<String, Command> clientCommands = getClientCommands();
            Messenger messenger = objectFactory.getLocalMessenger();
            Client client = new DefaultClient();
            RequestFactory requestFactory = DefaultRequestFactory.getInstance(clientCommands, objectFactory, messenger, address, port, client);
            CommandReader commandReader = new ConsoleCommandReader(requestFactory, objectFactory, messenger);
            client.start(commandReader);
        } catch (Exception e) {
            if(e.getMessage() != null)
                System.err.println(e.getMessage());
            System.err.println("Error got while executing the program.");
        }
    }


    public static Map<String, Command> getClientCommands() {
        HashMap<String, Command> commands = new HashMap<>();
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("help", new ClientHelpCommand());
        commands.put("exit", new DefaultExitCommand());
        return commands;
    }
}

