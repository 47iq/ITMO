package client.commands;

import common.Response;

import java.util.Map;
import java.util.ResourceBundle;

public class ClientHelpCommand implements MessagingCommand {

    private final Map<String, String> commands;

    public ClientHelpCommand(Map<String, String> commands) {
        this.commands = commands;
    }

    @Override
    public Response execute(Response response) {
        ResourceBundle bundle = getLocalResourceBundle();
        StringBuilder message = new StringBuilder();
        for(String command: commands.keySet()) {
            message.append(command).append(": ").append(bundle.getString(commands.get(command))).append("\n");
        }
        response.setMessage(message.toString());
        return response;
    }
}
