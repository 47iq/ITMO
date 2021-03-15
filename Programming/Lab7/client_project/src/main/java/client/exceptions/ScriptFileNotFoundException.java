package client.exceptions;

public class ScriptFileNotFoundException extends RuntimeException implements ClientException {

    private final String name;

    public ScriptFileNotFoundException(String name) {
        super("Error. Script file named " + name + " not found.");
        this.name = name;
    }

    public String accept(ClientExceptionMessenger visitor) {
        return visitor.doForScriptFileNF(name);
    }
}
