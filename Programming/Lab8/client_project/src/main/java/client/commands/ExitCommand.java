package client.commands;

import client.Client;

public interface ExitCommand extends ClientCommand {
    void execute(Client client);
}
