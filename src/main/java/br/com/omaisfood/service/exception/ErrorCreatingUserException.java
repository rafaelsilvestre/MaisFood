package br.com.omaisfood.service.exception;

public class ErrorCreatingUserException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ErrorCreatingUserException(String msg) {
        super(msg);
    }

    public ErrorCreatingUserException(String msg, Throwable cause) {
        super(msg, cause);
    }
}