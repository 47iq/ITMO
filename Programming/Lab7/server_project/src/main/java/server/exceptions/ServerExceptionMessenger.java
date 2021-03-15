package server.exceptions;

public interface ServerExceptionMessenger extends ExceptionMessenger {
    String doForUnknownCommand();
    String doForCommandExec();
    String doForUnknownExc();
    String doForBroken();
    String doForUnknownType();
    String doForID();
    String doForLogin();
    String doForRegistration();
    String doForNotLogged();
    String doForUserExists();
}
