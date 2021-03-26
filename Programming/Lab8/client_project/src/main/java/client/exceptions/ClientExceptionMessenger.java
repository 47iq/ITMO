package client.exceptions;

import server.exceptions.ExceptionMessenger;

public interface ClientExceptionMessenger extends ExceptionMessenger {
    String visit(CommandInputException e);

    String visit(CommunicationException e);

    String visit(FieldInputException e);

    String visit(LocalMessengerException e);

    String visit(LoginException e);

    String visit(ScriptException e, String filename);

    String visit(ScriptFileNotFoundException e, String filename);

    String visit(ScriptFileRecursionException e);

    String visit(TerminalException e);

    String visit(NumberFormatException e);

    String visit(NotEnoughAgrsException e);

    String visit(ConnectionException e);
}
