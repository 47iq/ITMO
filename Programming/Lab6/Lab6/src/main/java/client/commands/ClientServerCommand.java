package client.commands;

import common.Command;
import client.messages.Messenger;

public interface ClientServerCommand extends Command {
    void execute(Messenger messenger);
}
