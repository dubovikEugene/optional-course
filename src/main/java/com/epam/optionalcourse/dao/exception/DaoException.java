package com.epam.optionalcourse.dao.exception;

import java.io.Serial;

public class DaoException extends Exception{

    @Serial
    private static final long serialVersionUID = 5072803016833715747L;

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    protected DaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
