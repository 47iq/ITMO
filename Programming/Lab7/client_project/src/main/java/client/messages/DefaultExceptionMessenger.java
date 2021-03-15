package client.messages;

import client.exceptions.*;
import client.exceptions.ClientExceptionMessenger;
import server.exceptions.*;

public abstract class DefaultExceptionMessenger extends CommonExceptionMessenger implements ClientExceptionMessenger {
    public String doForCommandInput() {
        return new CommandInputException().getMessage();
    }

    public String doForCommunication() {
        return new CommunicationException().getMessage();
    }

    public String doForConnection() {
        return new ConnectionException().getMessage();
    }

    public String doForFieldInput() {
        return new FieldInputException().getMessage();
    }

    public String doForLocalMessenger() {
        return new LocalMessengerException().getMessage();
    }

    public String doForLogin() {
        return new LoginException().getMessage();
    }

    public String doForScript(String fileName) {
        return new ScriptException(fileName).getMessage();
    }

    public String doForScriptFileNF(String fileName) {
        return new ScriptFileNotFoundException(fileName).getMessage();
    }

    public String doForScriptRecursion() {
        return new ScriptFileRecursionException().getMessage();
    }

    public String doForTerminal() {
        return new TerminalException().getMessage();
    }

    public String doForNumberFormat() {
        return new FieldNumberFormatException().getMessage();
    }

    public String doForArgs() {
        return new NotEnoughAgrsException().getMessage();
    }
}
