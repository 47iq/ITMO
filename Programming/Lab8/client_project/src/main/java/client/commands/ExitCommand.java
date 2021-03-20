package client.commands;

import client.Client;
import common.Command;

public interface ExitCommand extends ClientCommand {
    void execute(Client client);
}
