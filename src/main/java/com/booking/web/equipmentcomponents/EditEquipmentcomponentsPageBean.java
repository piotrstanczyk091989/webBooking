package com.booking.web.equipmentcomponents;

import com.booking.model.EquipmentComponents;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("editEquipmentComponentsPageBean")
@RequestScoped
public class EditEquipmentcomponentsPageBean {

    public EditEquipmentcomponentsPageBean() {
    }
    
    @Inject
    private EquipmentComponentsSession equipmentComponentsSession;
    
    @PostConstruct
    private void init() {
        equipmentComponents = equipmentComponentsSession.getEquipmentComponentsEdit();
    }
    
    private EquipmentComponents equipmentComponents = new EquipmentComponents();

    public EquipmentComponents getEquipmentComponents() {
        return equipmentComponents;
    }
    
    public String saveEquipmentComponents() {
        return equipmentComponentsSession.saveEquipmentComponentsAfterEdit(equipmentComponents);
    }

}
