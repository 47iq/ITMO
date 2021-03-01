package server.command_manager;

import common.Response;
import common.Ticket;
import server.commands.Command;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.Map;

public interface CommandFactory {
    Response executeCommand(String commandName, Ticket ticket, String arg, String password) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
    void setMessenger(Locale locale);
    void setServerCommands(Map<String, Command> serverCommands);
}
