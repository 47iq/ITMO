package client.exceptions;

public class ScriptFileNotFoundException extends RuntimeException implements ClientException {

    public ScriptFileNotFoundException() {
        super("ERR_NO_SCRIPT");
    }
}
