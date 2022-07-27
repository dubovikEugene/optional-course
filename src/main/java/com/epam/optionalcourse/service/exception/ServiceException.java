package com.epam.optionalcourse.service.exception;

import java.io.Serial;

public class ServiceException extends Exception {

    @Serial
    private static final long serialVersionUID = 5816428243187925923L;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
