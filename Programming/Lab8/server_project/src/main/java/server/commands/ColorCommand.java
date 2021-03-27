package server.commands;

import common.Response;
import server.ObjectFactory;
import server.command_manager.Visitor;
import server.datawork.UsersDataBase;

public interface ColorCommand extends Command{
    Response execute(ObjectFactory factory, UsersDataBase dataBase, String login);

    @Override
    default Response accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
