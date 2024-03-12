package com.booking.web.equipment;

import com.booking.model.Equipment;
import com.booking.model.EquipmentComponents;
import com.booking.web.equipmentcomponents.EquipmentComponentsSession;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

@Named("editEquipmentPageBean")
@RequestScoped
public class EditEquipmentPageBean {

    public EditEquipmentPageBean() {
    }
    
    @Inject
    private EquipmentSession equipmentSession;
    
    @Inject
    private EquipmentComponentsSession equipmentComponentsSession;
    
    @PostConstruct
    private void init() {
        equipment = equipmentSession.getEquipmentEdit();
        
        allEquipmentComponentses = equipmentComponentsSession.findAllEquipmentComponents();
        allEquipmentComponentsesDataModel = new ListDataModel<>(allEquipmentComponentses);
    }
    
    private Equipment equipment = new Equipment();

    private List<EquipmentComponents> allEquipmentComponentses;
    private DataModel<EquipmentComponents> allEquipmentComponentsesDataModel;

    public DataModel<EquipmentComponents> getAllEquipmentComponentsesDataModel() {
        return allEquipmentComponentsesDataModel;
    }
    
    public Equipment getEquipment() {
        return equipment;
    }
    
    public String saveEquipment() {
        return equipmentSession.saveEquipmentAfterEdit(equipment);
    }

    public String addEquipmentComponentToEquipment() {
        return equipmentSession.addEquipmentComponentToEquipment(allEquipmentComponentsesDataModel.getRowData());
    }
    
    
}
