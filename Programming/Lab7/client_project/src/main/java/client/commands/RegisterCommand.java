package client.commands;

import client.Main;
import client.connection.ConnectionManager;
import client.exceptions.LoginException;
import client.reader.CommandReader;

public class RegisterCommand implements AuthCommand, ClientServerCommand {
    public void execute(CommandReader commandReader, ConnectionManager factory) {
        try {
            factory.setUser(commandReader.readUser());
        } catch (Exception e) {
            Main.getErr().println(new LoginException().accept(Main.getExceptionMessenger()));
        }
    }
}
