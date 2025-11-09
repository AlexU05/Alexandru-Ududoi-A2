package model.exception;

public class InvalidTypeException extends InterpreterException {
    public InvalidTypeException(String message) {
        super("Invalid type: " + message);
    }
    public InvalidTypeException() {
        super("Invalid type.");
    }
}
