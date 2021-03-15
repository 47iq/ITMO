package client.messages;

import java.util.HashMap;
import java.util.Map;

public abstract class ClientMessenger implements Messenger {

    protected Map<String, String> commands = new HashMap<>();

    public final String getCommands() {
        StringBuilder message = new StringBuilder();
        for (String command: commands.keySet())
            message.append(getCommandMessage(command));
        return message.toString() + "\n" + getCommandsMessageEnding();
    }

    protected String getCommandMessage(String command) {
        return  command + ": " + commands.get(command) +".\n";
    }

    protected abstract String getCommandsMessageEnding();

    protected abstract void setCommandTranslations();
}
