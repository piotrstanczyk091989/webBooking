package com.booking.web.account;

import com.booking.model.Account;
import com.booking.web.utils.ContextUtils;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("changePasswordAccountPageBean")
@RequestScoped
public class ChangePasswordAccountPageBean {

    public ChangePasswordAccountPageBean() {
    }

    @Inject
    private AccountSession accountSession;

    private Account account;

    @PostConstruct
    private void init() {
        account = accountSession.getAccountChangePassword();
        account.setPassword(new String());
    }

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

    public String changePassword() throws NoSuchAlgorithmException {
        if (!(passwordConfirm.equals(account.getPassword()))) {
            ContextUtils.emitInternationalizedMessage("zmienHasloKontaForm:passwordRepeat", "passwords.not.matching");
            return null;
        }

        return accountSession.changePasswordAccount(account.getPassword());
    }

}
