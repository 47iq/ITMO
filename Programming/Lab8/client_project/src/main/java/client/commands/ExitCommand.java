package client.commands;

import client.Client;
import client.ObjectFactory;
import common.Response;

public interface ExitCommand extends ClientCommand {
    Response execute(Client client, ObjectFactory factory);
}
