package client.exceptions;

public class ScriptException extends RuntimeException implements ClientException {

    public ScriptException() {
        super("ERR_SCRIPT");
    }

    public String accept(ClientExceptionMessenger visitor) {
        return visitor.visit(this, "");
    }
}
