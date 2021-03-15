package client.commands;

import client.messages.Messenger;
import common.Command;

public interface MessageCommand extends ClientServerCommand {
    void execute(Messenger messenger);
}
