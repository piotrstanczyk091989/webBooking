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

@Named("listEquipmentsWithEquipmentComponentPageBean")
@RequestScoped
public class ListEquipmentsWithEquipmentComponentPageBean {

    public ListEquipmentsWithEquipmentComponentPageBean() {
    }

    @Inject
    private EquipmentSession equipmentSession;

    @Inject
    private EquipmentComponentsSession equipmentComponentsSession;

    @PostConstruct
    private void init() {
        equipmentComponents = equipmentComponentsSession.getEquipmentComponentsEdit();

        equipmentsWithEquipmentComponent = equipmentComponentsSession.findEquipmentWithComponent(equipmentComponents);
        equipmentsWithEquipmentComponentDataModel = new ListDataModel<>(equipmentsWithEquipmentComponent);
    }

    private EquipmentComponents equipmentComponents = new EquipmentComponents();

    public EquipmentComponents getEquipmentComponents() {
        return equipmentComponents;
    }

    private List<Equipment> equipmentsWithEquipmentComponent;
    private DataModel<Equipment> equipmentsWithEquipmentComponentDataModel;

    public DataModel<Equipment> getEquipmentsWithEquipmentComponentDataModel() {
        return equipmentsWithEquipmentComponentDataModel;
    }

}
