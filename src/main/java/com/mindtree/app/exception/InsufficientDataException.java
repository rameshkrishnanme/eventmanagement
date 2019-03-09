package com.mindtree.app.exception;

public class InsufficientDataException extends RuntimeException {

    private static final long serialVersionUID = -4226214477466037704L;

    public InsufficientDataException(final String message) {
        super(message);
    }
}
