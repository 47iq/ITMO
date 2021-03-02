package client.commands;

import client.messages.Messenger;

public class ClientHelpCommand implements ClientServerCommand {


    public String getCommandName() {
        return "client_help";
    }

    public void execute(Messenger messenger) {
        System.out.println(messenger.getCommands());
    }
}
