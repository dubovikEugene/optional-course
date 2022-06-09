package com.epam.optionalcourse.dao.exception;

import java.io.Serial;

public class ConnectionPoolException extends Exception {

    @Serial
    private static final long serialVersionUID = -3166520919431388906L;

    public ConnectionPoolException(String s, Throwable exception) {
        super();
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }

    protected ConnectionPoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
