package com.booking.web.equipmentcomponents;

import com.booking.model.EquipmentComponents;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("createEquipmentComponentsPageBean")
@RequestScoped
public class CreateEquipmentComponentsPageBean {

    public CreateEquipmentComponentsPageBean() {
    }

    @Inject
    private EquipmentComponentsSession equipmentComponentsSession;

    private EquipmentComponents equipmentComponents = new EquipmentComponents();

    public EquipmentComponents getEquipmentComponents() {
        return equipmentComponents;
    }

    public String createEquipmnetComponents() {
        return equipmentComponentsSession.createEquipmentComponents(equipmentComponents);
    }

}
