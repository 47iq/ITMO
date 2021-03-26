package server.exceptions;

public class UnknownTypeException extends RuntimeException implements ServerException {

    public UnknownTypeException() {
        super("ERR_UNK_TYPE");
    }

    public String accept(ServerExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}