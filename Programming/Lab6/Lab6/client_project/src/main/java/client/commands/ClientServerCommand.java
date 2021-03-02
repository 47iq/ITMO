package client.commands;

import client.messages.Messenger;
import common.Command;

public interface ClientServerCommand extends Command {
    void execute(Messenger messenger);
}
