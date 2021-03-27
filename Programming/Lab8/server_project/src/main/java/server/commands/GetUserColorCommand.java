package server.commands;

import common.Response;
import server.ObjectFactory;
import server.datawork.UsersDataBase;
import server.exceptions.CommandExecutionException;

import java.awt.*;
import java.util.Map;

public class GetUserColorCommand implements ColorCommand{

    @Override
    public Response execute(ObjectFactory factory, UsersDataBase dataBase, String login) {
        try {
            Map<String, Color> colors;
            Response response;
            colors = dataBase.getUserColors();
            response = factory.getResponse(true, "");
            response.setColorMap(colors);
            return response;
        } catch (Exception e) {
            throw new CommandExecutionException();
        }
    }
}
