package model.exception;

public class FileAlreadyOpenException extends RuntimeException {
    public FileAlreadyOpenException(String fileName) {
        super("File '" + fileName + "' is already open.");
    }
}
