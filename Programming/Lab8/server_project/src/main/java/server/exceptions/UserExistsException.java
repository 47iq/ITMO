package server.exceptions;

public class UserExistsException extends RuntimeException implements ServerException {

    public UserExistsException() {
        super("ERR_USER_EXISTS");
    }

    public String accept(ServerExceptionMessenger visitor) {
        return visitor.visit(this);
    }
}
