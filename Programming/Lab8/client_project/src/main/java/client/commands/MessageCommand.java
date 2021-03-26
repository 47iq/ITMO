package client.commands;

import client.messages.Messenger;

public interface MessageCommand extends ClientServerCommand {
    void execute(Messenger messenger);
}
