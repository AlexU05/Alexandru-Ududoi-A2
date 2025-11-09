package model.exception;

public class FileNotOpenException extends RuntimeException {
    public FileNotOpenException(String fileName) {
        super("File '" + fileName + "' is not open.");
    }
}
