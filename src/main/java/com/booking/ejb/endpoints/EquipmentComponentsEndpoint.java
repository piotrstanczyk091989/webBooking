package com.booking.ejb.endpoints;

import com.booking.ejb.exception.AppBaseException;
import com.booking.ejb.facades.EquipmentComponentsFacade;
import com.booking.ejb.interceptor.LoggingInterceptor;
import com.booking.model.Equipment;
import com.booking.model.EquipmentComponents;
import com.booking.web.utils.EquipmentComponentsUtils;
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
public class EquipmentComponentsEndpoint extends AbstractEndpoint implements SessionSynchronization {

    @Inject
    private EquipmentComponentsFacade equipmentComponentsFacade;

    private EquipmentComponents equipmentComponentsState;

    private Equipment equipmentState;

    public List<EquipmentComponents> findAllEquipmentComponents() {
        return equipmentComponentsFacade.findAll();
    }

    public EquipmentComponents findEquipmentComponentsById(Long id) {
        return equipmentComponentsFacade.find(id);
    }

    public EquipmentComponents downloadEquipmentComponentsToEdit(EquipmentComponents equipmentComponents) {
        equipmentComponentsState = findEquipmentComponentsById(equipmentComponents.getId());
        return equipmentComponentsState;
    }

    @RolesAllowed("Administrator")
    public void saveEquipmentComponentsAfterEdit(EquipmentComponents equipmentComponents) throws AppBaseException {
        if (null == equipmentComponentsState) {
            throw new IllegalArgumentException("Brak wczytanego komponentu do modyfikacji");
        }
        if (!equipmentComponentsState.equals(equipmentComponents)) {
            throw new IllegalArgumentException("Modyfikowane komponentu niezgodne z wczytanym");
        }
        EquipmentComponentsUtils.rewriteDataAfterEdit(equipmentComponents, equipmentComponentsState);
        equipmentComponentsFacade.edit(equipmentComponentsState);
        equipmentComponentsState = null;
    }

    @RolesAllowed("Administrator")
    public void createEquipmentComponents(EquipmentComponents equipmentComponents) throws AppBaseException {
        equipmentComponentsFacade.create(equipmentComponents);
    }

    @RolesAllowed("Administrator")
    public void deleteEquipmentComponents(EquipmentComponents equipmentComponents) throws AppBaseException {
        equipmentComponentsFacade.remove(equipmentComponents);
    }

    public List<EquipmentComponents> matchComponent(String typeComponentPattern, String nameComponentPattern) {
        return equipmentComponentsFacade.matchComponent(typeComponentPattern, nameComponentPattern);
    }

    @RolesAllowed("Administrator")
    public void addEquipmentComponentToEquipmeent(Equipment equipment) {
        equipmentComponentsFacade.addEquipmentComponentToEquipmeent(equipment);
    }

    public List<Equipment> findEquipmentWithComponent(EquipmentComponents equipmentComponents) {
        Long id = equipmentComponents.getId();
        return equipmentComponentsFacade.findEquipmentWithComponent(id);
    }

}
