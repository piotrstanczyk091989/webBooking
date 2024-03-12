package com.booking.web.equipmentcomponents;

import com.booking.model.EquipmentComponents;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("deleteEquipmentComponentsPageBean")
@RequestScoped
public class DeleteEquipmentComponentsPageBean {

    public DeleteEquipmentComponentsPageBean() {
    }
    
    @Inject 
    private EquipmentComponentsSession equipmentComponentsSession;
    
    @PostConstruct
    private void init() {
        equipmentComponents = equipmentComponentsSession.getEquipmentComponentsDelete();
    }
    
    private EquipmentComponents equipmentComponents = new EquipmentComponents();

    public EquipmentComponents getEquipmentComponents() {
        return equipmentComponents;
    }
    
    public String deleteEquipmentComponents() {
        return equipmentComponentsSession.deleteEquipmentComponents(equipmentComponents);
    }
    
}
