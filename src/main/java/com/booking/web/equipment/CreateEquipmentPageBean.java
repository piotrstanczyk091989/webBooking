package com.booking.web.equipment;

import com.booking.model.Equipment;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("createEquipmentPageBean")
@RequestScoped
public class CreateEquipmentPageBean {

    public CreateEquipmentPageBean() {
    }
    
    @Inject
    private EquipmentSession  equipmentSession;
    
    private Equipment equipment = new Equipment();

    public Equipment getEquipment() {
        return equipment;
    }
    
    public String createEquipmnet() {
        return equipmentSession.createEquipment(equipment);
    }
    
    
    
}
