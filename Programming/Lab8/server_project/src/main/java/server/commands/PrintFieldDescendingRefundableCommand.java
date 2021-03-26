package server.commands;

import common.Response;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;

import java.util.List;

/**
 * Class of print_field_descending_refundable command
 *
 * @version 1.0
 * @autor 47iq
 */

public class PrintFieldDescendingRefundableCommand implements SimpleCommand {

    public PrintFieldDescendingRefundableCommand() {

    }

    public Response execute(CollectionManager collectionManager, String arg, ObjectFactory factory, String login) {
        List<Boolean> refundableList = collectionManager.getRefundableList();
        StringBuilder message = new StringBuilder();
        for (Boolean refundable : refundableList) {
            message.append(refundable).append("\n");
        }
        return factory.getResponse(true, message.toString());
    }

    public Response accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
