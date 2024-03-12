package com.booking.web.equipment;

import com.booking.model.Equipment;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

@Named("listEquipmentPageBean")
@RequestScoped
public class ListEquipmentPageBean {

    public ListEquipmentPageBean() {
    }
    
    @PostConstruct
    private void initModel() {
        equipment = equipmentSession.findAllEquipment();
        equipmentDataModel = new ListDataModel<>(equipment);
    }
    
    @Inject 
    private EquipmentSession equipmentSession;
    
    private List<Equipment> equipment;
    private DataModel<Equipment> equipmentDataModel;
   
    public DataModel<Equipment> getEquipmentDataModel() {
        return equipmentDataModel;
    }
    
    public String editEquipment() {
        return equipmentSession.downloadEquipmentToEdit(equipmentDataModel.getRowData());
    }
    
    public String deleteEquipment() {
        return equipmentSession.downloadEquipmentToDelete(equipmentDataModel.getRowData());
    }
    
     public void activateEquipment() {
        equipmentSession.activateEquipment(equipmentDataModel.getRowData());
        initModel();
    }
        
    public void deactivateEquipment() {
        equipmentSession.deactivateEquipment(equipmentDataModel.getRowData());
        initModel();
    }
    


}
