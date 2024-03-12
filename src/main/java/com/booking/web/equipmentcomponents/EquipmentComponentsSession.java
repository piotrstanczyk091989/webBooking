package com.booking.web.equipmentcomponents;

import com.booking.ejb.endpoints.EquipmentComponentsEndpoint;
import com.booking.ejb.endpoints.EquipmentEndpoint;
import com.booking.ejb.exception.AppBaseException;
import com.booking.ejb.exception.EquipmentComponentsException;
import com.booking.model.Equipment;
import com.booking.model.EquipmentComponents;
import com.booking.web.account.AccountSession;
import com.booking.web.utils.ContextUtils;
import java.util.logging.Level;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("equipmentComponentsSession")
@SessionScoped
public class EquipmentComponentsSession implements Serializable {

    private static final Logger logger = Logger.getLogger(EquipmentComponentsSession.class.getName());

    @Inject
    private EquipmentComponentsEndpoint equipmentComponentsEndpoint;

    @Inject
    private EquipmentEndpoint equipmentEndpoint;

    private EquipmentComponents equipmentComponentsEdit;

    private EquipmentComponents equipmentComponentsDelete;

    public EquipmentComponents getEquipmentComponentsEdit() {
        return equipmentComponentsEdit;
    }

    public EquipmentComponents getEquipmentComponentsDelete() {
        return equipmentComponentsDelete;
    }

    public List<EquipmentComponents> matchComponent(String typeComponentPattern, String nameComponentPattern) {
        return equipmentComponentsEndpoint.matchComponent(typeComponentPattern, nameComponentPattern);
    }

    public List<EquipmentComponents> findAllEquipmentComponents() {
        return equipmentComponentsEndpoint.findAllEquipmentComponents();
    }

    public List<EquipmentComponents> findAllEquipmentComponentsWhichAreNotInEquipment(Equipment equipment) {
        List<EquipmentComponents> e = equipmentComponentsEndpoint.findAllEquipmentComponents();
        e.removeAll(equipmentEndpoint.findEquipmentComponentsFromEquipment(equipment));
        return e;
    }

    public List<Equipment> findEquipmentWithComponent(EquipmentComponents equipmentComponents) {
        return equipmentComponentsEndpoint.findEquipmentWithComponent(equipmentComponents);
    }

    public String downloadEquipmentComponentsToEdit(EquipmentComponents equipmentComponents) {
        equipmentComponentsEdit = equipmentComponentsEndpoint.downloadEquipmentComponentsToEdit(equipmentComponents);
        return "editEquipmentComponents";
    }

    public String downloadEquipmentComponentsToDelete(EquipmentComponents equipmentComponents) {
        equipmentComponentsDelete = equipmentComponentsEndpoint.downloadEquipmentComponentsToEdit(equipmentComponents);
        return "deleteEquipmentComponents";
    }

    public String createEquipmentComponents(EquipmentComponents equipmentComponents) {
        try {
            equipmentComponentsEndpoint.createEquipmentComponents(equipmentComponents);
            return "success";
        } catch (EquipmentComponentsException ke) {
            if (EquipmentComponentsException.KEY_DB_CONSTRAINT.equals(ke.getMessage())) {
                ContextUtils.emitInternationalizedMessage("nameComponent", EquipmentComponentsException.KEY_DB_CONSTRAINT);
            } else {
                Logger.getLogger(EquipmentComponentsException.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji createEquipmentComponents wyjatku: ", ke);
            }
            return null;
        } catch (AppBaseException abe) {
            Logger.getLogger(EquipmentComponentsSession.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji createEquipmentComponents  wyjatku typu: ", abe.getClass());
            if (ContextUtils.isInternationalizationKeyExist(abe.getMessage())) {
                ContextUtils.emitInternationalizedMessage(null, abe.getMessage());
            }
            return null;
        }
    }

    public String deleteEquipmentComponents(EquipmentComponents equipmentComponents) {
        try {
            equipmentComponentsEndpoint.deleteEquipmentComponents(equipmentComponents);
            return "success";
        } catch (AppBaseException abe) {
            Logger.getLogger(EquipmentComponentsSession.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji deleteEquipmentComponents  wyjatku typu: ", abe.getClass());
            if (ContextUtils.isInternationalizationKeyExist(abe.getMessage())) {
                ContextUtils.emitInternationalizedMessage(null, abe.getMessage());
            }
            return null;
        }
    }

    public String saveEquipmentComponentsAfterEdit(EquipmentComponents equipmentComponents) {
        try {
            equipmentComponentsEndpoint.saveEquipmentComponentsAfterEdit(equipmentComponents);
            return "success";
        } catch (EquipmentComponentsException ke) {
            if (EquipmentComponentsException.KEY_DB_CONSTRAINT.equals(ke.getMessage())) {
                ContextUtils.emitInternationalizedMessage("nameComponent", EquipmentComponentsException.KEY_DB_CONSTRAINT);
            } else {
                Logger.getLogger(EquipmentComponentsException.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji createEquipmentComponents wyjatku: ", ke);
            }
            return null;
        } catch (AppBaseException abe) {
            Logger.getLogger(EquipmentComponentsSession.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji createEquipmentComponents  wyjatku typu: ", abe.getClass());
            if (ContextUtils.isInternationalizationKeyExist(abe.getMessage())) {
                ContextUtils.emitInternationalizedMessage(null, abe.getMessage());
            }
            return null;
        }
    }

    public String listEquipmentWithComponent(EquipmentComponents equipmentComponents) {
        equipmentComponentsEdit = equipmentComponentsEndpoint.downloadEquipmentComponentsToEdit(equipmentComponents);
        return "listEquipmentWithEquipmentComponents";
    }

}
