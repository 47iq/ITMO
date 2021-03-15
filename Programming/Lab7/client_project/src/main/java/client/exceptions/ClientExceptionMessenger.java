package client.exceptions;

import server.exceptions.ExceptionMessenger;

public interface ClientExceptionMessenger extends ExceptionMessenger {
    String doForCommandInput();
    String doForCommunication();
    String doForConnection();
    String doForFieldInput();
    String doForLocalMessenger();
    String doForLogin();
    String doForScript(String name);
    String doForScriptFileNF(String name);
    String doForScriptRecursion();
    String doForTerminal();
    String doForNumberFormat();
    String doForArgs();
}
