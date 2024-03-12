package com.booking.web.account;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import com.booking.model.Account;


@RequestScoped
@Named("listAccountPageBean")
public class ListAccountPageBean {

    static final String GENERAL_MSG_ID = "listaKontForm:listaKont";

    public ListAccountPageBean() {
    }

    @Inject
    private AccountSession accountSession;

    @PostConstruct
    private void initModel() {
        accounts = accountSession.matchAccount(searchLogin, searchName, searchSurname, searchEmail);
        accountsDataModel = new ListDataModel<>(accounts);
    }
    private List<Account> accounts;
    private DataModel<Account> accountsDataModel;

    public DataModel<Account> getAccountsDataModel() {
        return accountsDataModel;
    }

    private String searchLogin = "";
    private String searchName = "";
    private String searchSurname = "";
    private String searchEmail = "";

    public String getSearchLogin() {
        return searchLogin;
    }

    public void setSearchLogin(String searchLogin) {
        this.searchLogin = searchLogin;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchSurname() {
        return searchSurname;
    }

    public void setSearchSurname(String searchSurname) {
        this.searchSurname = searchSurname;
    }

    public String getSearchEmail() {
        return searchEmail;
    }

    public void setSearchEmail(String searchEmail) {
        this.searchEmail = searchEmail;
    }

    public void refresh() {
        initModel();
    }

    public void clean() {
        searchLogin = "";
        searchName = "";
        searchSurname = "";
        searchEmail = "";
    }

    public void activateAccount() {
        accountSession.activeteAccount(accountsDataModel.getRowData());
        initModel();
    }

    public void deactivateAccount() {
        accountSession.deactiveteAccount(accountsDataModel.getRowData());
        initModel();
    }

    public void confirmAccount() {
        accountSession.confirmAccount(accountsDataModel.getRowData());
        initModel();
    }

    public String editAccount() {
        return accountSession.downloadAccountToEdit(accountsDataModel.getRowData());
    }

    public String beginChangePassword() {
        return accountSession.beginChangePassword(accountsDataModel.getRowData());
    }
}
