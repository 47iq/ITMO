package client.commands;

import client.Main;
import client.messages.Messenger;

public class ClientHelpCommand implements ClientServerCommand, MessageCommand, LoginFreeCommand {
    public void execute(Messenger messenger) {
        Main.getOut().println(messenger.getCommands());
    }
}
