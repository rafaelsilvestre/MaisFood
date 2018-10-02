package br.com.omaisfood.service.exception;

public class PermissionDaniedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PermissionDaniedException(String msg) {
        super(msg);
    }

    public PermissionDaniedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}