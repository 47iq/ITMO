package client.commands;

import client.Client;
import common.Command;

public interface ExitCommand extends Command {
    void execute(Client client);
}
