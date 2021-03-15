package client.exceptions;

public class ScriptFileRecursionException extends RuntimeException implements ClientException {
    public ScriptFileRecursionException() {
        super("Error. Script file contains recursion.");
    }

    public String accept(ClientExceptionMessenger visitor) {
        return visitor.doForScriptRecursion();
    }
}
