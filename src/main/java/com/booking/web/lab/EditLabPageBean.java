package com.booking.web.lab;

import com.booking.model.Lab;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("editLabPageBean")
@RequestScoped
public class EditLabPageBean {

    public EditLabPageBean() {
    }

    @Inject
    private LabSession labSession;

    @PostConstruct
    private void init() {
        lab = labSession.getLabEdit();
    }

    private Lab lab = new Lab();

    public Lab getLab() {
        return lab;
    }

    public String saveLab() {
        return labSession.saveLabAfterEdit(lab);
    }

}
