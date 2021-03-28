package client.gui_controllers;

import client.reader.CommandReader;
import common.Ticket;

import java.util.List;
import java.util.ResourceBundle;

public interface ControllerContext {
    void setCurrentCollection(List<Ticket> ticketList);

    List<Ticket> getCurrentCollection();

    void setCurrentCommand(String command);

    String getCurrentCommand();

    void setCurrentUser(String user);

    String getCurrentUser();

    void setCommandReader(CommandReader reader);

    CommandReader getCommandReader();

    ControlManager getControlManager();

    Ticket getResetUpdateArg();

    void setUpdateArg(Ticket ticket);

    void setPrevScene(String scene);

    String getPrevScene();

    ResourceBundle getBundle();

    void setBundle(ResourceBundle bundle);

    String getErrorMessage(Exception e);

    String getErrorMessage(String e);
}
