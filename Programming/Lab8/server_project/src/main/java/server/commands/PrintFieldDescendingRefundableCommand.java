package server.commands;

import common.Response;
import server.Main;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;
import server.exceptions.CommandExecutionException;

import java.util.List;

/**
 * Class of print_field_descending_refundable command
 * @autor 47iq
 * @version 1.0
 */

public class PrintFieldDescendingRefundableCommand implements SimpleCommand{

    public PrintFieldDescendingRefundableCommand() {

    }

    public Response execute(CollectionManager collectionManager, String arg, ObjectFactory factory, String login){
        List<Boolean> refundableList = collectionManager.getRefundableList();
        StringBuilder message = new StringBuilder();
        for (Boolean refundable : refundableList) {
            message.append(refundable).append("\n");
        }
        return factory.getResponse(true, message.toString());
    }

    public Response accept(Visitor visitor) {
        return visitor.doForSimple(this);
    }
}
