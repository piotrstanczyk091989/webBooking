package com.booking.web.lab;

import com.booking.model.Lab;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

@Named("listLabPageBean")
@RequestScoped
public class ListLabPageBean {

    public ListLabPageBean() {
    }
    
    @PostConstruct
    private void initModel() {
        lab = labSession.findAllLab();
        labDataModel = new ListDataModel<>(lab);
    }
    
    @Inject
    private LabSession labSession;
    
    private List<Lab> lab;
    private DataModel<Lab> labDataModel;

    public DataModel<Lab> getLabDataModel() {
        return labDataModel;
    }
    
    public String editLab() {
        return labSession.downloadLabToEdit(labDataModel.getRowData());
    }
    
    public String deleteLab() {
        return labSession.downloadLabToDelete(labDataModel.getRowData());
    }
    
    public String listEquipmentInLab() {
        return labSession.listEquipmentInLab(labDataModel.getRowData());
    }
    
    public String listBookingInLab() {
        return labSession.listBookingInLab(labDataModel.getRowData());
    }
    
    
}
