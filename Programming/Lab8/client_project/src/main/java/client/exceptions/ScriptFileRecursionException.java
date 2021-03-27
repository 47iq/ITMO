package client.exceptions;

public class ScriptFileRecursionException extends RuntimeException implements ClientException {
    public ScriptFileRecursionException() {
        super("ERR_SCRIPT_RECURSION");
    }
}
