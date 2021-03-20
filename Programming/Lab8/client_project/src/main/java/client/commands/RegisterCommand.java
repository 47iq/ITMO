package client.commands;

import client.Main;
import client.ObjectFactory;
import client.connection.ConnectionManager;
import client.exceptions.LoginException;
import client.reader.CommandReader;

public class RegisterCommand implements AuthCommand, ClientServerCommand {
    public void execute(CommandReader commandReader, ConnectionManager manager, String args, ObjectFactory factory) {
        try {
            String[] data = args.split("\\s+");
            manager.setUser(factory.getUser(data[0], data[1]));
        } catch (Exception e) {
            throw new LoginException();
        }
    }
}
