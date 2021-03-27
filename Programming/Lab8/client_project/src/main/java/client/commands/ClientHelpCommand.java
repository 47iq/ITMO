package client.commands;

import common.Response;

import java.util.Map;
import java.util.ResourceBundle;

public class ClientHelpCommand implements MessagingCommand {

    private Map<String, String> commands;
    public ClientHelpCommand(Map<String, String> commands) {
        this.commands = commands;
    }

    @Override
    public Response execute(Response response) {
        ResourceBundle bundle = getLocalResourceBundle();
        String message = "";
        for(String command: commands.keySet()) {
            message += command + ": " + commands.get(command);
        }
        response.setMessage(message);
        return response;
    }
}
