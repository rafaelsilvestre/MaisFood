package br.com.omaisfood.service.exception;

public class StorageFileNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public StorageFileNotFoundException(String msg) {
        super(msg);
    }

    public StorageFileNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}