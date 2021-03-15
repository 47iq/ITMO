package server.messages;

import server.exceptions.*;

public abstract class DefaultExceptionMessenger extends CommonExceptionMessenger implements ServerExceptionMessenger {

    public String doForUnknownCommand() {
        return new UnknownCommandException().getMessage();
    }

    public String doForCommandExec() {
        return new CommandExecutionException().getMessage();
    }

    public String doForUnknownExc() {
        return new UnknownException().getMessage();
    }

    public String doForBroken() {
        return new BrokenPackageException().getMessage();
    }

    public String doForUnknownType() {
        return new UnknownTypeException().getMessage();
    }

    public String doForID() {
        return new InvalidIdException().getMessage();
    }

    public String doForLogin() {
        return new LoginException().getMessage();
    }

    public String doForRegistration() {
        return new RegistrationException().getMessage();
    }

    public String doForNotLogged() {
        return new NotLoggedInException().getMessage();
    }

    public String doForUserExists() {
        return new UserExistsException().getMessage();
    }
}
