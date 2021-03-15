package client.exceptions;

public class ScriptException extends RuntimeException implements ClientException {

    private final String name;

    public ScriptException(String name) {
        super("Error. Can't execute script from file: " + name);
        this.name = name;
    }

    public String accept(ClientExceptionMessenger visitor) {
        return visitor.doForScript(name);
    }
}
