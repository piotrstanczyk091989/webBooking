package com.booking.ejb.exception;

import com.booking.model.Account;

public class AccountException extends AppBaseException {

    static final public String KEY_DB_CONSTRAINT = "error.konto.db.constraint.uniq.login";

    private AccountException(String message) {
        super(message);
    }

    private AccountException(String message, Throwable cause) {
        super(message, cause);
    }
    
    private Account account;

    public Account getAccount() {
        return account;
    }

    static public AccountException createWithDbCheckConstraintKey(Account account, Throwable cause) {
        AccountException ze = new AccountException(KEY_DB_CONSTRAINT, cause);
        ze.account=account;
        return ze;
    }
}
