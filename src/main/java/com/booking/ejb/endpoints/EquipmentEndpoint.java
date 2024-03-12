package com.booking.ejb.endpoints;

import com.booking.ejb.exception.AppBaseException;
import com.booking.ejb.facades.EquipmentFacade;
import com.booking.ejb.interceptor.LoggingInterceptor;
import com.booking.model.Equipment;
import com.booking.model.EquipmentComponents;
import com.booking.web.utils.EquipmentUtils;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

@Stateful
@LocalBean
@Interceptors(LoggingInterceptor.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@RolesAllowed({"Uzytkownik", "Administrator"})
public class EquipmentEndpoint extends AbstractEndpoint implements SessionSynchronization {

    @Inject
    private EquipmentFacade equipmentFacade;

    private Equipment equipmentState;

    public List<EquipmentComponents> findEquipmentComponentsFromEquipment(Equipment equipment) {
        Long id = equipment.getId();
        return equipmentFacade.findEquipmentComponentFromEquipment(id);
    }

    @RolesAllowed("Administrator")
    public void activateEquipment(Equipment equipment) {
        Equipment e = equipmentFacade.find(equipment.getId());
        e.setConditionEquipment(true);
    }

    @RolesAllowed("Administrator")
    public void deactivateEquipment(Equipment equipment) {
        Equipment e = equipmentFacade.find(equipment.getId());
        e.setConditionEquipment(false);
    }

    public List<Equipment> findAllEquipment() {
        return equipmentFacade.findAll();
    }

    public Equipment findEquipmentById(Long id) {
        return equipmentFacade.find(id);
    }

    public Equipment downloadEquipmentToEdit(Equipment equipment) {
        equipmentState = findEquipmentById(equipment.getId());
        return equipmentState;
    }

    @RolesAllowed("Administrator")
    public void saveEquipmentAfterEdit(Equipment equipment) throws AppBaseException {
        if (null == equipmentState) {
            throw new IllegalArgumentException("Brak wczytanego sprzętu do modyfikacji");
        }
        if (!equipmentState.equals(equipment)) {
            throw new IllegalArgumentException("Modyfikowane sprzętu niezgodne z wczytanym");
        }
        EquipmentUtils.rewriteDataAfterEdit(equipment, equipmentState);
        equipmentFacade.edit(equipmentState);
        equipmentState = null;
    }

    @RolesAllowed("Administrator")
    public void createEquipment(Equipment equipment) throws AppBaseException {
        equipmentFacade.create(equipment);
    }

    @RolesAllowed("Administrator")
    public void deleteEquipment(Equipment equipment) throws AppBaseException {
        equipmentFacade.remove(equipment);
    }

    public void addEquipmentComponent(EquipmentComponents equipmentComponents) {
        equipmentState.getEquipmentComponents().add(equipmentComponents);
        equipmentComponents.getEquipments().add(equipmentState);
        equipmentFacade.addEquipmentComponent(equipmentComponents);
    }

    public void removeEquipmentComponentFromEquipment(EquipmentComponents equipmentComponents) {
        equipmentState.getEquipmentComponents().remove(equipmentComponents);
        equipmentComponents.getEquipments().remove(equipmentState);
        equipmentFacade.removeEquipmentComponent(equipmentComponents);
    }

}
