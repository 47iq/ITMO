package server.commands;

import common.Response;
import common.User;
import server.ObjectFactory;
import server.command_manager.Visitor;
import server.datawork.UsersDataBase;
import server.exceptions.CommandExecutionException;

import java.awt.*;
import java.util.Map;

public class UpdateColorCommand implements ColorCommand{

    @Override
    public Response execute(ObjectFactory factory, UsersDataBase dataBase, String login) {
        try {
            Color color;
            Response response;
            color = dataBase.getNewColor(login);
            response = factory.getResponse(true, "");
            response.setColor(color);
            return response;
        } catch (Exception e) {
            throw new CommandExecutionException();
        }
    }
}
