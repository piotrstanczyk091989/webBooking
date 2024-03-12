package com.booking.web.account;

import com.booking.model.Account;
import com.booking.web.utils.ContextUtils;
import java.security.NoSuchAlgorithmException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named("changeMyPasswordPageBean")
@RequestScoped
public class ChangeMyPasswordPageBean {
    
    public ChangeMyPasswordPageBean() {
    }
    
    @Inject
    private AccountSession accountSession;

    private Account account = new Account();

    public Account getAccount() {
        return account;
    }
        
    private String passwordConfirm = "";

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    
    private String oldPassword = "";

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
    
    public String changePassword() throws NoSuchAlgorithmException {
        if (!(passwordConfirm.equals(account.getPassword()))){
            ContextUtils.emitInternationalizedMessage("zmienMojeHasloForm:passwordRepeat", "passwords.not.matching");
            return null;
        }
            
        return accountSession.changeMyPassword(oldPassword, account.getPassword());
    }
    
}
