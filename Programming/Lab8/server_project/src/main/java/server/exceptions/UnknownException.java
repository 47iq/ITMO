package server.exceptions;

public class UnknownException extends RuntimeException implements ServerException {

    public UnknownException() {
        super("ERR_UNK");
    }

    public String accept(ServerExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
