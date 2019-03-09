package com.mindtree.app.exception;

public class DuplicateDataException extends RuntimeException {

    private static final long serialVersionUID = -8509003882185452543L;

    public DuplicateDataException(final String message) {
        super(message);
    }
}
