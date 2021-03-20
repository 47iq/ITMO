package client.commands;

import client.ObjectFactory;
import client.connection.ConnectionManager;
import client.reader.CommandReader;

/**
 * Interface of the script executing command
 */

public interface ScriptCommand extends ClientCommand {

    void execute(CommandReader reader, String arg, ConnectionManager commandFactory, ObjectFactory clientObjectFactory);
}
