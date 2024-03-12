package com.booking.web.lab;

import com.booking.ejb.exception.AppBaseException;
import com.booking.model.Equipment;
import com.booking.model.Lab;
import com.booking.web.equipment.EquipmentSession;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

@Named("listEquipmentInLabPageBean")
@RequestScoped
public class ListEquipmentInLabPageBean {

    public ListEquipmentInLabPageBean() {
    }
    
    @Inject 
    private LabSession labSession;

    @Inject 
    private EquipmentSession equipmentSession;
    
    @PostConstruct
    private void init() {
        lab = labSession.getLabEdit();
        
        equipmentsInLab = equipmentSession.findEquipmentFromLab(lab);
        equipmentsInLabDataModel = new ListDataModel<>(equipmentsInLab);
    }
    
    private Lab lab = new Lab();

    public Lab getLab() {
        return lab;
    }
    
    public String saveLab() throws AppBaseException {
        return labSession.saveLabAfterEdit(lab);
    }   

    private List<Equipment> equipmentsInLab;
    private DataModel<Equipment> equipmentsInLabDataModel;

    public DataModel<Equipment> getEquipmentsInLabDataModel() {
        return equipmentsInLabDataModel;
    }
    
    public String downloadLabToCreateEquipmentIn() throws AppBaseException {
        return labSession.downloadLabToCreateEquipmentIn(lab);        
    }

    public void activateEquipment() {
        equipmentSession.activateEquipment(equipmentsInLabDataModel.getRowData());
        init();
    }    
    
    public void deactivateEquipment() {
        equipmentSession.deactivateEquipment(equipmentsInLabDataModel.getRowData());
        init();
    }
    
    public String listEquipmentComponentsFromEquipment() {
        return equipmentSession.listEquipmentComponentsFromEquipment(equipmentsInLabDataModel.getRowData());
    }
    
    public String downloadEquipmentToCreateReservation() {
        return equipmentSession.downloadEquipmentToCreateReservation(equipmentsInLabDataModel.getRowData());
    }
   
    public String listReservationEquipment() {
        return equipmentSession.downloadEquipmentToListReservation(equipmentsInLabDataModel.getRowData());
    }
    
}

