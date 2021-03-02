package client.commands;

import client.Client;

public class DefaultExitCommand implements ExitCommand {

    public void execute(Client client) {
        client.stop();
    }

    public String getCommandName() {
        return "exit";
    }
}
