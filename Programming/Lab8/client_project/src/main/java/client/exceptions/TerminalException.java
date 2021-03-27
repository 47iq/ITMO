package client.exceptions;

public class TerminalException extends RuntimeException implements ClientException {
    public TerminalException() {
        super("ERR_TERMINAL");
    }
}
