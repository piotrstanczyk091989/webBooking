
package com.booking.ejb.endpoints;

import com.booking.ejb.exception.AppBaseException;
import com.booking.ejb.facades.AccountFacade;
import com.booking.ejb.facades.AdministratorFacade;
import com.booking.ejb.facades.UserFacade;
import com.booking.ejb.interceptor.LoggingInterceptor;
import com.booking.model.Account;
import com.booking.model.User;
import com.booking.web.utils.AccountUtils;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;


@Stateful
@LocalBean
@Interceptors(LoggingInterceptor.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AccountEndpoint extends AbstractEndpoint implements SessionSynchronization {
    
    @Inject
    private AccountFacade accountFacade;
    
    @Inject
    private UserFacade userFacade;

    @Inject
    private AdministratorFacade administratorFacade;
    
    private Account accountState;

    public Account downloadMyAccount() {
        return findLogin(downloadMyLogin());
    }

    public String downloadMyLogin() throws IllegalStateException {
        return sctx.getCallerPrincipal().getName();
    }

    @RolesAllowed("Administrator") 
    public void createAccount(Account account)throws NoSuchAlgorithmException, AppBaseException  {
        account.setActive(true);
        account.setConfirmed(true);        
        account.setPassword(AccountUtils.computeDigestPassword(account.getPassword()));
        accountFacade.create(account);
    }

    @PermitAll
    public void registerUser(User user) throws NoSuchAlgorithmException, AppBaseException {
        user.setActive(true);
        user.setConfirmed(false);
        user.setPassword(AccountUtils.computeDigestPassword(user.getPassword()));
        userFacade.create(user);
    }
    
    @RolesAllowed("Administrator")
    public void activateAccount(Account account){
        Account k = accountFacade.find(account.getId());
        k.setActive(true);
    }
    
    @RolesAllowed("Administrator")
    public void deactivateAccount(Account account){
        Account k = accountFacade.find(account.getId());
        k.setActive(false);
    }
    
    @RolesAllowed("Administrator")
    public void confirmAccount(Account account){
        Account k = accountFacade.find(account.getId());
        k.setConfirmed(true);
    }    
    
    @RolesAllowed("Administrator")
    public List<Account> downloadAllAccount() {
        return accountFacade.findAll();
    }
    
    @RolesAllowed("Administrator")
    public List<Account> matchAccount(String loginPattern, String namePattern, String surnamePattern, String emailPattern) {
        return accountFacade.matchAccount(loginPattern, namePattern, surnamePattern, emailPattern);
    }
    
    public Account findLogin(String login) {
        return accountFacade.findLogin(login);
    }
    
    public Account downloadAccountToEdit(Account account) {
        accountState = findLogin(account.getLogin());
        return accountState;
    }
    
    public void saveAccountAfterEdit(Account account) throws AppBaseException {
        if (null == accountState) {
            throw new IllegalArgumentException("Brak wczytanego konta do modyfikacji");
        }
        if (!accountState.equals(account)) {
            throw new IllegalArgumentException("Modyfikowane konto niezgodne z wczytanym");
        }
        AccountUtils.rewriteDataAfterEdit(account, accountState);
        accountFacade.edit(accountState);
        accountState=null;
    }
    
    public void changeMyPassword(String oldPassword, String newPassword) throws NoSuchAlgorithmException {
        Account myAccount = downloadMyAccount();
        if(!myAccount.getPassword().equals(AccountUtils.computeDigestPassword(oldPassword)))
            throw new IllegalArgumentException("Podane dotychczasowe hasło nie zgadza się");
        myAccount.setPassword(AccountUtils.computeDigestPassword(newPassword));
    }
    
    public void changePassword(Account account, String password) throws NoSuchAlgorithmException {
        Account k = accountFacade.find(account.getId());
        k.setPassword(AccountUtils.computeDigestPassword(password));
    }
}
