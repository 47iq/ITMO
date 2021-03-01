package server.commands;

import server.command_manager.Visitor;
import common.Response;

/**
 * Interface of commands
 * @autor 47iq
 * @version 1.0
 */

public interface Command {

    Response accept(Visitor visitor);
}
