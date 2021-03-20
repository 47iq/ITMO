package server.exceptions;

public class UnknownCommandException extends IllegalArgumentException implements ServerException {

    public UnknownCommandException() {
        super("Error. Unknown command has been entered.");
    }


    public String accept(ServerExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
