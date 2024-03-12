package com.booking.web.account;

import com.booking.model.Administrator;
import com.booking.web.utils.ContextUtils;
import java.security.NoSuchAlgorithmException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("createAdministratorPageBean")
@RequestScoped
public class CreateAdminstratorPageBean {

    public CreateAdminstratorPageBean() {
    }

    @Inject
    private AccountSession accountSession;

    private Administrator account = new Administrator();

    public Administrator getAccount() {
        return account;
    }

    private String passwordConfirm = "";

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String createAdmin() throws NoSuchAlgorithmException {
        if (!(passwordConfirm.equals(account.getPassword()))) {
            ContextUtils.emitInternationalizedMessage("createAdministratorForm:passwordRepeat", "passwords.not.matching");
            return null;
        }

        return accountSession.createAdministrator(account);
    }

}
