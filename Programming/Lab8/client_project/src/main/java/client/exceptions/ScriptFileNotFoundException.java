package client.exceptions;

public class ScriptFileNotFoundException extends RuntimeException implements ClientException {

    public ScriptFileNotFoundException(String name) {
        super("ERR_NO_SCRIPT");
    }

    public String accept(ClientExceptionMessenger visitor) {
        return visitor.visit(this, "");
    }
}
