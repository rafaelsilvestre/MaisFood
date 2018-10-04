package br.com.omaisfood.service.exception;

public class InactiveCompanyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InactiveCompanyException(String msg) {
        super(msg);
    }

    public InactiveCompanyException(String msg, Throwable cause) {
        super(msg, cause);
    }
}