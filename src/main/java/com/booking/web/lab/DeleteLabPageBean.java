package com.booking.web.lab;

import com.booking.model.Lab;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("deleteLabPageBean")
@RequestScoped
public class DeleteLabPageBean {

    public DeleteLabPageBean() {
    }

    @Inject
    private LabSession labSession;

    @PostConstruct
    private void init() {
        lab = labSession.getLabDelete();
    }

    private Lab lab = new Lab();

    public Lab getLab() {
        return lab;
    }

    public String deleteLab() {
        return labSession.deleteLab(lab);
    }
}
