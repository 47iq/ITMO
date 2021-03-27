package client.commands;

import client.ObjectFactory;
import client.connection.ConnectionManager;
import client.reader.CommandReader;
import common.Response;

public interface AuthCommand extends ClientCommand {
    Response execute(CommandReader commandReader, ConnectionManager manager, String args, ObjectFactory factory);
}
