package com.booking.web.equipment;

import com.booking.model.Equipment;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("deleteEquipmentPageBean")
@RequestScoped
public class DeleteEquipmentPageBean {

    public DeleteEquipmentPageBean() {
    }

    @Inject
    private EquipmentSession equipmentSession;

    @PostConstruct
    private void init() {
        equipment = equipmentSession.getEquipmentDelete();
    }

    private Equipment equipment = new Equipment();

    public Equipment getEquipment() {
        return equipment;
    }

    public String deleteEquipment() {
        return equipmentSession.deleteEquipment(equipment);
    }

}
