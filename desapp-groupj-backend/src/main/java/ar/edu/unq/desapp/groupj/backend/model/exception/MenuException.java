package ar.edu.unq.desapp.groupj.backend.model.exception;

public class MenuException extends RuntimeException  {
    public MenuException() { super(); }
    public MenuException(String message) { super(message); }
    public MenuException(String message, Throwable cause) { super(message, cause); }
    public MenuException(Throwable cause) { super(cause); }
}
