package server.commands;

import common.Response;
import server.command_manager.Visitor;

/**
 * Interface of commands
 *
 * @version 1.0
 * @autor 47iq
 */

public interface Command {
    Response accept(Visitor visitor);
}
