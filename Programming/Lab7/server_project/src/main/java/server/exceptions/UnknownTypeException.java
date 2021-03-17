package server.exceptions;

public class UnknownTypeException extends RuntimeException implements ServerException {

    public UnknownTypeException() {
        super("Error. Unknown request type. Update your app client to the latest version.");
    }

    public String accept(ServerExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}