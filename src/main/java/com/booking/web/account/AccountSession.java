package com.booking.web.account;

import com.booking.ejb.endpoints.AccountEndpoint;
import com.booking.ejb.exception.AccountException;
import com.booking.ejb.exception.AppBaseException;
import com.booking.model.Account;
import com.booking.model.Administrator;
import com.booking.model.User;
import com.booking.web.utils.ContextUtils;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named("accountSession")
@SessionScoped
public class AccountSession implements Serializable {

    private static final Logger LOG = Logger.getLogger(AccountSession.class.getName());

    @Inject
    private AccountEndpoint accountEndpoint;

    public String registerSession() {
        ContextUtils.invalidateSession();
        return "cancelAction";
    }

    public String getMyLogin() {
        return ContextUtils.getUserName();
    }

    private User userRegistration;

    private User userCreate;

    private Administrator administratorCreate;

    private Account accountEdit;

    private Account accountChangePassword;

    public Account getAccountChangePassword() {
        return accountChangePassword;
    }

    public Account getAccountEdit() {
        return accountEdit;
    }

    public User getUserRegistration() {
        return userRegistration;
    }

    public AccountSession() {
    }

    public String createUser(User user) throws NoSuchAlgorithmException {
        try {
            userCreate = user;
            accountEndpoint.createAccount(userCreate);
            userCreate = null;
            return "success";
        } catch (AccountException ke) {
            if (AccountException.KEY_DB_CONSTRAINT.equals(ke.getMessage())) {
                ContextUtils.emitInternationalizedMessage("login", AccountException.KEY_DB_CONSTRAINT); 
            } else {
                Logger.getLogger(AccountException.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji createUser wyjatku: ", ke);
            }
            return null;
        } catch (AppBaseException abe) {
            Logger.getLogger(AccountSession.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji createUser wyjatku typu: ", abe.getClass());
            if (ContextUtils.isInternationalizationKeyExist(abe.getMessage())) {
                ContextUtils.emitInternationalizedMessage(null, abe.getMessage()); 
            }
            return null;
        }
    }

    @RolesAllowed("Administrator")
    public String createAdministrator(Administrator admin) throws NoSuchAlgorithmException {
        try {
            administratorCreate = admin;
            accountEndpoint.createAccount(administratorCreate);
            administratorCreate = null;
            return "success";
        } catch (AccountException ke) {
            if (AccountException.KEY_DB_CONSTRAINT.equals(ke.getMessage())) {
                ContextUtils.emitInternationalizedMessage("login", AccountException.KEY_DB_CONSTRAINT); 
            } else {
                Logger.getLogger(AccountException.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji utworzAdministratora wyjatku: ", ke);
            }
            return null;
        } catch (AppBaseException abe) {
            Logger.getLogger(AccountSession.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji utworzAdministratora wyjatku typu: ", abe.getClass());
            if (ContextUtils.isInternationalizationKeyExist(abe.getMessage())) {
                ContextUtils.emitInternationalizedMessage(null, abe.getMessage()); 
            }
            return null;
        }
    }

    public String confirmRegistrationUser(User user) {
        this.userRegistration = user;
        return "confirmRegister";
    }

    public String beginChangePassword(Account account) {
        this.accountChangePassword = account;
        return "changePassword";
    }

    public String registerUser() throws NoSuchAlgorithmException, AppBaseException {
        accountEndpoint.registerUser(userRegistration);
        userRegistration = null;
        return "success";
    }

    public void activeteAccount(Account Account) {
        accountEndpoint.activateAccount(Account);
        ContextUtils.emitSuccessMessage(ListAccountPageBean.GENERAL_MSG_ID);
    }

    public void deactiveteAccount(Account Account) {
        accountEndpoint.deactivateAccount(Account);
        ContextUtils.emitSuccessMessage(ListAccountPageBean.GENERAL_MSG_ID);
    }

    public void confirmAccount(Account Account) {
        accountEndpoint.confirmAccount(Account);
        ContextUtils.emitSuccessMessage(ListAccountPageBean.GENERAL_MSG_ID);
    }

    public String downloadAccountToEdit(Account Account) {
        accountEdit = accountEndpoint.downloadAccountToEdit(Account);
        return "editAccount";
    }

    public String saveAccountAfterEdit(Account Account) throws AppBaseException {
        accountEndpoint.saveAccountAfterEdit(Account);
        return "success";
    }

    public String changePasswordAccount(String password) throws NoSuchAlgorithmException {
        accountEndpoint.changePassword(accountChangePassword, password);
        return "success";
    }

    public String changeMyPassword(String oldPassword, String newPassword) throws NoSuchAlgorithmException {
        accountEndpoint.changeMyPassword(oldPassword, newPassword);
        return "success";
    }

    public List<Account> downloadAllAccount() {
        return accountEndpoint.downloadAllAccount();
    }

    public List<Account> matchAccount(String loginPattern, String namePattern, String surnamePattern, String emailPattern) {
        return accountEndpoint.matchAccount(loginPattern, namePattern, surnamePattern, emailPattern);
    }

    public Account downloadMyAccount() {
        return accountEndpoint.downloadMyAccount();
    }

    @PostConstruct
    private void init() {
        LOG.severe("Session started: " + ContextUtils.getSessionID());
    }
}
