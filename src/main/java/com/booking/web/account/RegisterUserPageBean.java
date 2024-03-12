package com.booking.web.account;

import com.booking.model.User;
import com.booking.web.utils.ContextUtils;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named("registerUserPageBean")
@RequestScoped
public class RegisterUserPageBean {
    
    public RegisterUserPageBean() {
    }
    
    @Inject
    private AccountSession accountSession;

    private User account =  new User();

    public User getAccount() {
        return account;
    }
    
    private String passwordConfirm = "";

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    
    public String confirmRegistration() {
        if (!(passwordConfirm.equals(account.getPassword()))){
            ContextUtils.emitInternationalizedMessage("rejestrujUzytkownikaForm:passwordRepeat", "passwords.not.matching");
            return null;
        }
            
        return accountSession.confirmRegistrationUser(account);
    }

}
