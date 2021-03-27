package client.commands;

import client.Client;
import client.ObjectFactory;
import common.Response;

public class DefaultExitCommand implements ExitCommand {

    public Response execute(Client client, ObjectFactory factory) {
        client.stop();
        return factory.getResponse(true,"");
    }
}
