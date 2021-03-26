package server.exceptions;

public class CommandExecutionException extends RuntimeException implements ServerException {

    public CommandExecutionException() {
        super("ERR_EXECUTION");
    }


    public String accept(ServerExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
