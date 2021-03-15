package server.exceptions;

public class BrokenPackageException extends RuntimeException implements ServerException {

    public BrokenPackageException() {
        super("Error. Damaged package has been sent by client.");
    }

    public String accept(ServerExceptionMessenger visitor) {
        return visitor.doForBroken();
    }
}
