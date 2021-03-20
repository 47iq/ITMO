package client.commands;

import client.ObjectFactory;
import client.connection.ConnectionManager;
import client.reader.CommandReader;

public interface AuthCommand extends ClientCommand {
    void execute(CommandReader commandReader, ConnectionManager manager, String args, ObjectFactory factory);
}
