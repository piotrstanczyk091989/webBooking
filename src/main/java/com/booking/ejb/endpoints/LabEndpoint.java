package com.booking.ejb.endpoints;

import com.booking.ejb.exception.AppBaseException;
import com.booking.ejb.facades.EquipmentFacade;
import com.booking.ejb.facades.LabFacade;
import com.booking.ejb.interceptor.LoggingInterceptor;
import com.booking.model.Equipment;
import com.booking.model.Lab;
import com.booking.web.utils.LabUtils;
import java.util.List;
import javax.annotation.security.RolesAllowed;
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
public class LabEndpoint extends AbstractEndpoint implements SessionSynchronization {

    @Inject
    private LabFacade labFacade;

    @Inject
    private EquipmentFacade equipmentFacade;

    private Lab labState;

    public List<Lab> downloadAllLab() {
        return labFacade.findAll();
    }

    public List<Equipment> findEquipmentFromLab(String number) {
        return equipmentFacade.findEquipmentFromLab(number);
    }

    public Lab findLabById(Long id) {
        return labFacade.find(id);
    }

    public Lab downloadLabToEdit(Lab lab) {
        labState = findLabById(lab.getId());
        return labState;
    }

    @RolesAllowed("Administrator")
    public void createLab(Lab lab) throws AppBaseException {
        labFacade.create(lab);
    }

    @RolesAllowed("Administrator")
    public void saveLabAfterEdit(Lab lab) throws AppBaseException {
        if (null == labState) {
            throw new IllegalArgumentException("Brak wczytanego laboratorium do modyfikacji");
        }
        if (!labState.equals(lab)) {
            throw new IllegalArgumentException("Modyfikowane laboratorium niezgodne z wczytanym");
        }
        LabUtils.rewriteDataAfterEdit(lab, labState);
        labFacade.edit(labState);
        labState = null;
    }

    @RolesAllowed("Administrator")
    public void deleteLab(Lab lab) throws AppBaseException {
        labFacade.remove(lab);
    }

}
