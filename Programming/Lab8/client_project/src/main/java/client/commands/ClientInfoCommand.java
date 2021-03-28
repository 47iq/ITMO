package client.commands;

import common.Response;

import java.util.ResourceBundle;

public class ClientInfoCommand implements MessagingCommand {
    @Override
    public Response execute(Response response) {
        ResourceBundle bundle = getLocalResourceBundle();
        String[] info = response.getInfo();
        String result = bundle.getString("COLLECTION_TYPE") + ": " + info[0] + "\n" +
                bundle.getString("COLLECTION_SIZE") + ": " + info[1] + "\n" +
                bundle.getString("COLLECTION_DATE") + ": " + info[2];
        response.setMessage(result);
        return response;
    }
}
