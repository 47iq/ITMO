package client.commands;

import client.ObjectFactory;
import client.connection.ConnectionManager;
import client.exceptions.LoginException;
import client.reader.CommandReader;
import common.Response;

public class LoginCommand implements AuthCommand, ClientServerCommand {

    @Override
    public Response execute(CommandReader commandReader, ConnectionManager manager, String args, ObjectFactory factory) {
        try {
            String[] data = args.split("\\s+");
            System.out.println();
            manager.setUser(factory.getUser(data[0], data[1]));
            return factory.getResponse(true,"");
        } catch (Exception e) {
            throw new LoginException();
        }
    }
}
