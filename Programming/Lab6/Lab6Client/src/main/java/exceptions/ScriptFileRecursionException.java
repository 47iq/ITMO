package exceptions;

public class ScriptFileRecursionException extends RuntimeException{
    public ScriptFileRecursionException() {
        super("Error. Script file contains recursion.");
    }
}
