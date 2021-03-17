package server.messages;

import server.exceptions.*;

public abstract class DefaultExceptionMessenger extends CommonExceptionMessenger implements ServerExceptionMessenger {

    public String visit(UnknownCommandException e) {
        return e.getMessage();
    }

    public String visit(CommandExecutionException e) {
        return e.getMessage();
    }

    public String visit(BrokenPackageException e) {
        return e.getMessage();
    }

    public String visit(UnknownTypeException e) {
        return e.getMessage();
    }

    public String visit(UnknownException e) {
        return e.getMessage();
    }

    public String visit(InvalidIdException e) {
        return e.getMessage();
    }

    public String visit(LoginException e) {
        return e.getMessage();
    }

    public String visit(RegistrationException e) {
        return e.getMessage();
    }

    public String visit(NotLoggedInException e) {
        return e.getMessage();
    }

    public String visit(UserExistsException e) {
        return e.getMessage();
    }
}
