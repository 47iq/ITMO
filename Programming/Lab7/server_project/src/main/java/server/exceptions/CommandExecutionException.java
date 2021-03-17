package server.exceptions;

public class CommandExecutionException extends RuntimeException implements ServerException{

    public CommandExecutionException() {
        super("Error got during the command execution.");
    }


    public String accept(ServerExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
