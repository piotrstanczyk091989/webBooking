package com.booking.web.account;

import com.booking.ejb.exception.AppBaseException;
import com.booking.model.User;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named("registerUserConfirmPageBean")
@RequestScoped
public class RegisterUserConfirmPageBean {
    
    public RegisterUserConfirmPageBean() {
    }
    
    @Inject
    private AccountSession accountSession;

    @PostConstruct
    private void initBean() {
        account = accountSession.getUserRegistration();        
    }
    
    private User account;

    public User getAccount() {
        return account;
    }
        
    public String register() throws NoSuchAlgorithmException, AppBaseException {
        return accountSession.registerUser();
    }

}
