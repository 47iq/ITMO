package client.command_manager;

import client.Client;
import client.Main;
import client.ObjectFactory;
import client.commands.AuthCommand;
import client.commands.ExitCommand;
import client.commands.MessageCommand;
import client.commands.ScriptCommand;
import client.connection.ConnectionManager;
import client.exceptions.ScriptFileRecursionException;
import client.messages.Messenger;
import client.reader.CommandReader;
import common.Command;
import common.Response;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClientCommandFactory implements CommandFactory {

    private final Client client;

    private final Map<String, Command> commands;

    protected static Set<File> files = new HashSet<>();

    private final ObjectFactory objectFactory;

    private final Messenger messenger;

    public ClientCommandFactory(Client client, Map<String, Command> commands, ObjectFactory objectFactory, Messenger messenger) {
        this.client = client;
        this.commands = commands;
        this.objectFactory = objectFactory;
        this.messenger = messenger;
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }

    public Response executeCommand(Command command, CommandReader commandReader, String arg, ConnectionManager factory) {
        if (command instanceof ExitCommand)
            return ((ExitCommand) command).execute(client, objectFactory);
        if (command instanceof AuthCommand)
            return ((AuthCommand) command).execute(commandReader, factory, arg, objectFactory);
        if (command instanceof ScriptCommand)
            return executeScriptCommand((ScriptCommand) command, commandReader, arg, factory);
        if (command instanceof MessageCommand)
            return ((MessageCommand) command).execute(objectFactory);
        return null;
    }

    private Response executeScriptCommand(ScriptCommand command, CommandReader commandReader, String arg,
                                      ConnectionManager factory) {
        File file = new File(arg);
        if (files.contains(file)) {
            throw new ScriptFileRecursionException();
        }
        files.add(file);
        command.execute(commandReader, arg, factory, objectFactory);
        files.remove(file);
        return objectFactory.getResponse(true, "");
    }

}
