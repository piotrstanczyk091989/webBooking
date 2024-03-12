package com.booking.web.equipment;

import com.booking.web.lab.*;
import com.booking.model.Equipment;
import com.booking.model.EquipmentComponents;
import com.booking.model.Lab;
import com.booking.web.equipmentcomponents.EquipmentComponentsSession;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

@Named("listEquipmentComponentsFromEquipmentPageBean")
@RequestScoped
public class ListEquipmentComponentsFromEquipmentPageBean {

    public ListEquipmentComponentsFromEquipmentPageBean() {
    }
    @Inject
    private LabSession labSession;

    @Inject 
    private EquipmentSession equipmentSession;

    @Inject 
    private EquipmentComponentsSession equipmentComponentsSession;
    
    @PostConstruct
    private void init() {
        lab = labSession.getLabEdit();
        equipment = equipmentSession.getEquipmentEdit();
        
        equipmentComponentsInEquipment = equipmentSession.findEquipmentComponentsesFromEquipment(equipment);
        equipmentComponentsInEquipmentDataModel = new ListDataModel<>(equipmentComponentsInEquipment);
        
        allEquipmentComponents = equipmentComponentsSession.findAllEquipmentComponentsWhichAreNotInEquipment(equipment);
        allEquipmentComponentsDataModel = new ListDataModel<>(allEquipmentComponents);
    }
    
    private Lab lab = new Lab();
    
    private Equipment equipment = new Equipment();

    public Equipment getEquipment() {
        return equipment;
    }

    public Lab getLab() {
        return lab;
    }
   
    private List<EquipmentComponents> equipmentComponentsInEquipment;
    private DataModel<EquipmentComponents> equipmentComponentsInEquipmentDataModel;

    private List<EquipmentComponents> allEquipmentComponents;
    private DataModel<EquipmentComponents> allEquipmentComponentsDataModel;
    
    public DataModel<EquipmentComponents> getEquipmentComponentsInEquipmentDataModel() {
        return equipmentComponentsInEquipmentDataModel;
    }

    public DataModel<EquipmentComponents> getAllEquipmentComponentsDataModel() {
        return allEquipmentComponentsDataModel;
    }
    
    public void addEquipmentComponents() {
        equipmentSession.addEquipmentComponentToEquipment(allEquipmentComponentsDataModel.getRowData());
        init();
    }
    
    public void removeEquipmentComponentFromEquipment() {
        equipmentSession.removeEquipmentComponentFromEquipment(equipmentComponentsInEquipmentDataModel.getRowData());
        init();
    }
   
}

