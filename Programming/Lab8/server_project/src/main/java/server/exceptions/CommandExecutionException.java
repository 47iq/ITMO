package server.exceptions;

public class CommandExecutionException extends RuntimeException implements ServerException {

    public CommandExecutionException() {
        super("ERR_EXECUTION");
    }
}
