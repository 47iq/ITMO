package client.messages;

import client.exceptions.*;
import client.exceptions.ClientExceptionMessenger;
import server.exceptions.*;

public abstract class DefaultExceptionMessenger extends CommonExceptionMessenger implements ClientExceptionMessenger {

    public String visit(CommandInputException e) {
        return e.getMessage();
    }

    public String visit(CommunicationException e) {
        return e.getMessage();
    }

    public String visit(FieldInputException e) {
        return e.getMessage();
    }

    public String visit(LocalMessengerException e) {
        return e.getMessage();
    }

    public String visit(LoginException e) {
        return e.getMessage();
    }

    public String visit(ScriptException e, String file) {
        return e.getMessage();
    }

    public String visit(ScriptFileNotFoundException e, String file) {
        return e.getMessage();
    }

    public String visit(ScriptFileRecursionException e) {
        return e.getMessage();
    }

    public String visit(TerminalException e) {
        return e.getMessage();
    }

    public String visit(NumberFormatException e) {
        return e.getMessage();
    }

    public String visit(NotEnoughAgrsException e) {
        return e.getMessage();
    }

    public String visit(ConnectionException e) {
        return e.getMessage();
    }
}
