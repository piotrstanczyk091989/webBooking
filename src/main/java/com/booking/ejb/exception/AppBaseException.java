
package com.booking.ejb.exception;

import javax.ejb.ApplicationException;


@ApplicationException(rollback=true)
abstract public class AppBaseException extends Exception {
    
    protected AppBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    protected AppBaseException(String message) {
        super(message);
    }
    
}
