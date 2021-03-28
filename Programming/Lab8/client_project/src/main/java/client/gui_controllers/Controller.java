package client.gui_controllers;

public interface Controller {
    void setContext(ControllerContext context);

    ControllerContext getContext();

    void initialize(ControllerContext context);
}
