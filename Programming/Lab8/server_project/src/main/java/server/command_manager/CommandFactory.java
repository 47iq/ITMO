package server.command_manager;

import common.Response;
import common.Ticket;
import common.UpdateData;
import server.commands.Command;
import server.datawork.UsersDataBase;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface CommandFactory {
    Response executeCommand(String commandName, Ticket ticket, String arg, String user, UpdateData updateData, UsersDataBase dataBase) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;

    void setServerCommands(Map<String, Command> serverCommands);
}
