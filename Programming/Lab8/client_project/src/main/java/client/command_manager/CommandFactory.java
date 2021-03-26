package client.command_manager;

import client.connection.ConnectionManager;
import client.reader.CommandReader;
import common.Command;

public interface CommandFactory {
    Command getCommand(String commandName);

    void executeCommand(Command name, CommandReader commandReader, String arg, ConnectionManager factory);
}
