package server.exceptions;

public class UserExistsException extends RuntimeException implements ServerException {

    public UserExistsException() {
        super("ERR_USER_EXISTS");
    }
}
