package client.gui_controllers;

import client.reader.CommandReader;
import common.Ticket;

import java.util.List;
import java.util.ResourceBundle;

public class DefaultControllerContext implements ControllerContext {

    private List<Ticket> tickets;

    private String command;

    private String user;

    private CommandReader reader;

    private final ControlManager controlManager;

    private Ticket updateArg;

    private String prevScene;

    private ResourceBundle bundle;

    public DefaultControllerContext(CommandReader commandReader, ControlManager controlManager, ResourceBundle bundle) {
        setCommandReader(commandReader);
        this.controlManager = controlManager;
        this.bundle = bundle;
    }

    @Override
    public ControlManager getControlManager() {
        return controlManager;
    }

    @Override
    public void setCurrentCollection(List<Ticket> ticketList) {
        tickets = ticketList;
    }

    @Override
    public List<Ticket> getCurrentCollection() {
        return tickets;
    }

    @Override
    public void setCurrentCommand(String command) {
        this.command = command;
    }

    @Override
    public String getCurrentCommand() {
        return command;
    }

    @Override
    public void setCurrentUser(String user) {
        this.user = user;
    }

    @Override
    public String getCurrentUser() {
        return user;
    }

    @Override
    public void setCommandReader(CommandReader reader) {
        this.reader = reader;
    }

    @Override
    public CommandReader getCommandReader() {
        return reader;
    }

    @Override
    public Ticket getResetUpdateArg() {
        Ticket ticket = updateArg;
        updateArg = null;
        return ticket;
    }

    @Override
    public void setUpdateArg(Ticket updateArg) {
        this.updateArg = updateArg;
    }

    @Override
    public String getPrevScene() {
        return prevScene;
    }

    @Override
    public void setPrevScene(String prevScene) {
        this.prevScene = prevScene;
    }

    @Override
    public ResourceBundle getBundle() {
        return bundle;
    }

    @Override
    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }
}
