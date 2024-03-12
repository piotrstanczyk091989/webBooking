package com.booking.web.account;

import com.booking.ejb.exception.AppBaseException;
import com.booking.model.Account;
import com.booking.web.utils.AccountUtils;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named("editAccountPageBean")
@RequestScoped
public class EditAccountPageBean {
    
    public EditAccountPageBean() {
    }
    
    @Inject
    private AccountSession accountSession;

    @PostConstruct
    private void init() {
        account = accountSession.getAccountEdit();
    }

    private Account account =  new Account();

    public Account getAccount() {
        return account;
    }
    
    public boolean isUser() {
        return AccountUtils.isUser(account);
    }

    public boolean isAdministrator() {
        return AccountUtils.isAdministrator(account);
    }
    
    public String saveAccount() throws AppBaseException {
        return accountSession.saveAccountAfterEdit(account);
    }

}
