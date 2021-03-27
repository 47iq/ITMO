package client.commands;

import client.ObjectFactory;
import client.messages.Messenger;
import common.Response;

import java.util.Map;

public interface MessageCommand extends ClientCommand {
    Response execute(ObjectFactory factory);
}
