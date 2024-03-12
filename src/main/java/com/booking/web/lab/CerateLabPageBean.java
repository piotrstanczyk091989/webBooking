package com.booking.web.lab;

import com.booking.model.Lab;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("createLabPageBean")
@RequestScoped
public class CerateLabPageBean {

    public CerateLabPageBean() {
    }

    @Inject
    private LabSession labSession;

    private Lab lab = new Lab();

    public Lab getLab() {
        return lab;
    }

    public String createLab() {
        return labSession.createLab(lab);
    }

}
