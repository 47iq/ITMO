package server.exceptions;

public class UnknownTypeException extends RuntimeException implements ServerException {

    public UnknownTypeException() {
        super("ERR_UNK_TYPE");
    }
}