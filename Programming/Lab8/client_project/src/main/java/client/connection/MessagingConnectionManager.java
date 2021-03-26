package client.connection;

import client.commands.ClientInfoCommand;
import client.commands.ClientShowCommand;
import client.commands.MessagingCommand;
import client.reader.CommandReader;
import common.Response;
import common.User;

import java.io.IOException;

public class MessagingConnectionManager implements ConnectionManager {

    private final ConnectionManager connectionManager;

    private final MessagingCommand infoCommand;
    private final MessagingCommand showCommand;

    public MessagingConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        infoCommand = new ClientInfoCommand();
        showCommand = new ClientShowCommand();
    }

    @Override
    public Response executeCommand(String command, CommandReader commandReader, String arg) throws IOException {
        Response response = connectionManager.executeCommand(command, commandReader, arg);
        if (response.isSuccessful()) {
            switch (command) {
                case "info" -> infoCommand.execute(response);
                case "show" -> showCommand.execute(response);
            }
        }
        return response;
    }

    @Override
    public void setUser(User user) {
        connectionManager.setUser(user);
    }
}
