package com.booking.ejb.exception;

import com.booking.model.Lab;


public class LabException extends AppBaseException {

    static final public String KEY_DB_CONSTRAINT = "error.lab.db.constraint.uniq.number";
    static final public String KEY_DB_REMOVE = "error.lab.db.remove";
    
    private LabException(String message) {
        super(message);
    }

    private LabException(String message, Throwable cause) {
        super(message, cause);
    }
    private Lab lab;

    public Lab getLab() {
        return lab;
    }

    static public LabException createWithDbCheckConstraintKey(Lab lab, Throwable cause) {
        LabException ze = new LabException(KEY_DB_CONSTRAINT, cause);
        ze.lab=lab;
        return ze;
    }

    static public LabException removeWithDbCheckConstraintKey(Lab lab, Throwable cause) {
        LabException ze = new LabException(KEY_DB_REMOVE, cause);
        ze.lab=lab;
        return ze;
    }
    
}
