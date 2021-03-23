package server.commands;

import common.Response;
import server.command_manager.Visitor;

/**
 * Interface of commands
 * @autor 47iq
 * @version 1.0
 */

public interface Command {
    Response accept(Visitor visitor);
}
