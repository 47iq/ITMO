package server.exceptions;

public class UnknownCommandException extends IllegalArgumentException implements ServerException {

    public UnknownCommandException() {
        super("ERR_UNK_COMMAND");
    }
}
