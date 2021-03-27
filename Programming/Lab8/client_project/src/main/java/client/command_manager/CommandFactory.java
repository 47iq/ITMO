package client.command_manager;

import client.connection.ConnectionManager;
import client.reader.CommandReader;
import common.Command;
import common.Response;

public interface CommandFactory {
    Command getCommand(String commandName);

    Response executeCommand(Command name, CommandReader commandReader, String arg, ConnectionManager factory);
}
