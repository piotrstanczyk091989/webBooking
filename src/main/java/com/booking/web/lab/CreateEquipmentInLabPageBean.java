package com.booking.web.lab;

import com.booking.model.Equipment;
import com.booking.model.Lab;
import com.booking.web.equipment.EquipmentSession;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("createEquipmentInLabPageBean")
@RequestScoped
public class CreateEquipmentInLabPageBean {

    public CreateEquipmentInLabPageBean() {
    }

    @Inject
    private LabSession labSession;

    @Inject
    private EquipmentSession equipmentSession;

    @PostConstruct
    private void init() {
        lab = labSession.getLabEdit();
    }

    private Lab lab = new Lab();

    public Lab getLab() {
        return lab;
    }

    private Equipment equipment = new Equipment();

    public Equipment getEquipment() {
        return equipment;
    }

    public String createEquipmnetInLab() {
        return labSession.createEquipmentInLab(equipment);
    }

    public String backToLabEquipments() {
        return labSession.listEquipmentInLab(lab);
    }

}
