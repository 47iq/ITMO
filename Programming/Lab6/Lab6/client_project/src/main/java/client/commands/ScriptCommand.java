package client.commands;

import client.ObjectFactory;
import client.connection.RequestFactory;
import client.connection.RequestSender;
import client.reader.CommandReader;
import common.Command;

/**
 * Interface of the script executing command
 */

public interface ScriptCommand extends Command {

    /**
     * Method that executes command
     * @param reader command reader
     * @param arg argument
     * @param commandFactory command factory
     * @param clientObjectFactory object factory
     */
    void execute(RequestSender requestSender, CommandReader reader, String arg, RequestFactory commandFactory, ObjectFactory clientObjectFactory);
}
