package server.exceptions;

public class UnknownException extends RuntimeException implements ServerException {

    public UnknownException() {
        super("Unknown error.");
    }

    public String accept(ServerExceptionMessenger visitor) {
        return visitor.doForUnknownExc();
    }
}
