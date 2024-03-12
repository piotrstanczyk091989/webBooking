package com.booking.web.account;

import com.booking.model.Account;
import com.booking.web.utils.AccountUtils;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named("detailsMyAccountPageBean")
@RequestScoped
public class DetailsMyAccountPageBean {
    
    public DetailsMyAccountPageBean() {
    }
    
    @Inject
    private AccountSession accountSession;
    
    @PostConstruct
    private void init() {
        account = accountSession.downloadMyAccount();
    }

    private Account account = new Account();


    public Account getAccount() {
        return account;
    }    
    
    public boolean isUser() {
        return AccountUtils.isUser(account);
    }

    public boolean isAdministrator() {
        return AccountUtils.isAdministrator(account);
    }

}
