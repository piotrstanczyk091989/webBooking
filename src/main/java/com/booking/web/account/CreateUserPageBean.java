package com.booking.web.account;

import com.booking.model.User;
import com.booking.web.utils.ContextUtils;
import java.security.NoSuchAlgorithmException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("createUserPageBean")
@RequestScoped
public class CreateUserPageBean {

    public CreateUserPageBean() {
    }

    @Inject
    private AccountSession accountSession;

    private User account = new User();

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

    public String createUser() throws NoSuchAlgorithmException {
        if (!(passwordConfirm.equals(account.getPassword()))) {
            ContextUtils.emitInternationalizedMessage("utworzUzytkownikaForm:passwordRepeat", "passwords.not.matching");
            return null;
        }

        return accountSession.createUser(account);
    }

}
