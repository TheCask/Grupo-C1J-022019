package ar.edu.unq.desapp.groupj.backend.model.exception;

public class OrderDetailException extends RuntimeException {
    public OrderDetailException() { super(); }
    public OrderDetailException(String message) { super(message); }
    public OrderDetailException(String message, Throwable cause) { super(message, cause); }
    public OrderDetailException(Throwable cause) { super(cause); }
}