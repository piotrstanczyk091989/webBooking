package com.booking.web.equipment;

import com.booking.ejb.endpoints.BookingLabEndpoint;
import com.booking.ejb.endpoints.EquipmentComponentsEndpoint;
import com.booking.ejb.endpoints.EquipmentEndpoint;
import com.booking.ejb.endpoints.LabEndpoint;
import com.booking.ejb.endpoints.ReservationEndpoint;
import com.booking.ejb.exception.AppBaseException;
import com.booking.ejb.exception.ReservationException;
import com.booking.model.BookingLab;
import com.booking.model.Equipment;
import com.booking.model.EquipmentComponents;
import com.booking.model.Lab;
import com.booking.model.Reservation;
import com.booking.web.utils.ContextUtils;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("equipmentSession")
@SessionScoped
public class EquipmentSession implements Serializable {

    private static final Logger logger = Logger.getLogger(EquipmentSession.class.getSimpleName());

    @Inject
    private EquipmentEndpoint equipmentEndpoint;

    @Inject
    private EquipmentComponentsEndpoint equipmentComponentsEndpoint;

    @Inject
    private LabEndpoint labEndpoint;

    @Inject
    private BookingLabEndpoint bookingLabEndpoint;

    @Inject
    private ReservationEndpoint reservationEndpoint;

    private Reservation reservationEdit;

    private Equipment equipmentEdit;

    private Equipment equipmentDelete;

    private Lab labEdit;
    
    private EquipmentComponents equipmentComponentsEdit;

    public String createReservationEquipment(Reservation reservation) {
        List<Reservation> res = reservationEndpoint.findReservtionForEquipment(equipmentEdit);
        Date startR = reservation.getStartReservation();
        Date stopR = reservation.getStopReservation();

        List<BookingLab> bl = bookingLabEndpoint.findBookingInLab(labEdit.getNumber());
        Date startB = reservation.getStartReservation();
        Date stopB = reservation.getStopReservation();

    try {
        if(startR.after(stopR) ) {
            throw ReservationException.createReservationExceptionTimeStartStop(reservation);
        }

        for (Reservation r : res) {
            if (r.getStartReservation().after(startR) && r.getStartReservation().after(stopR)); 
            else if (r.getStopReservation().before(startR) && r.getStopReservation().before(stopR)); 
            else {
                throw ReservationException.createReservationExceptionDuplicateReservationEquipment(reservation);
            }
        }

        for (BookingLab b : bl) {
            if (b.getStartBookingLab().after(startB) && b.getStartBookingLab().after(stopB)); 
            else if (b.getStopBookingLab().before(startB) && b.getStopBookingLab().before(stopB)); 
            else {
                throw ReservationException.createReservationExceptionBookedLab(reservation);
            }
        }

        equipmentEdit.getReservations().add(reservation);
        reservation.setEquipmentReservation(equipmentEdit);
        reservationEndpoint.whoAddReservation(reservation);
        reservationEndpoint.createReservation(reservation);
        return "listReservationForEquipment";
        
        } catch (ReservationException re) {
            if (ReservationException.KEY_DUPLICATE.equals(re.getMessage())) {
                ContextUtils.emitInternationalizedMessage("msgs", ReservationException.KEY_DUPLICATE); //wyjątki aplikacyjne powinny przenosić jedynie klucz do internacjonalizacji
            } else if (ReservationException.KEY_BOOKED_LAB.equals(re.getMessage())) {
                ContextUtils.emitInternationalizedMessage("msgs", ReservationException.KEY_BOOKED_LAB); //wyjątki aplikacyjne powinny przenosić jedynie klucz do internacjonalizacji
            } else if (ReservationException.KEY_TIME_START_STOP.equals(re.getMessage())) {
                ContextUtils.emitInternationalizedMessage("msgs", ReservationException.KEY_TIME_START_STOP); //wyjątki aplikacyjne powinny przenosić jedynie klucz do internacjonalizacji
            } 
            else {
                Logger.getLogger(EquipmentSession.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji createReservationEquipment wyjatku: ", re);
            }
            return null;
        } catch (AppBaseException abe) {
            Logger.getLogger(EquipmentSession.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji createReservationEquipment wyjatku typu: ", abe.getClass());
            if (ContextUtils.isInternationalizationKeyExist(abe.getMessage())) {
                ContextUtils.emitInternationalizedMessage(null, abe.getMessage());
            }
            return null;
        }
        
    }

    public List<EquipmentComponents> findEquipmentComponentsesFromEquipment(Equipment equipment) {
        return equipmentEndpoint.findEquipmentComponentsFromEquipment(equipment);
    }

    public String listEquipmentComponentsFromEquipment(Equipment equipment) {
        equipmentEdit = equipmentEndpoint.downloadEquipmentToEdit(equipment);
        return "listEquipmentComponentsFromEquipment";
    }

    public String downloadEquipmentToCreateReservation(Equipment equipment) {
        equipmentEdit = equipmentEndpoint.downloadEquipmentToEdit(equipment);
        return "createEquipmentReservation";
    }

    public String downloadEquipmentToListReservation(Equipment equipment) {
        equipmentEdit = equipmentEndpoint.downloadEquipmentToEdit(equipment);
        return "listReservationForEquipment";
    }

    public Equipment getEquipmentEdit() {
        return equipmentEdit;
    }

    public Equipment getEquipmentDelete() {
        return equipmentDelete;
    }

    public Lab getLabEdit() {
        return labEdit;
    }

    public void activateEquipment(Equipment equipment) {
        equipmentEndpoint.activateEquipment(equipment);
    }

    public void deactivateEquipment(Equipment equipment) {
        equipmentEndpoint.deactivateEquipment(equipment);
    }

    public List<Equipment> findEquipmentFromLab(Lab lab) {
        labEdit = labEndpoint.downloadLabToEdit(lab);
        return labEndpoint.findEquipmentFromLab(labEdit.getNumber());
    }

    public List<Equipment> findAllEquipment() {
        return equipmentEndpoint.findAllEquipment();
    }

    public String downloadEquipmentToEdit(Equipment equipment) {
        equipmentEdit = equipmentEndpoint.downloadEquipmentToEdit(equipment);
        return "editEquipment";
    }

    public String downloadEquipmentToDelete(Equipment equipment) {
        equipmentDelete = equipmentEndpoint.downloadEquipmentToEdit(equipment);
        return "deleteEquipment";
    }

    public String createEquipment(Equipment equipment) {
        try{
        equipmentEndpoint.createEquipment(equipment);
        return "success";
        }
        catch (AppBaseException abe) {
            Logger.getLogger(EquipmentSession.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji createEquipment wyjatku typu: ", abe.getClass());
            if (ContextUtils.isInternationalizationKeyExist(abe.getMessage())) {
                ContextUtils.emitInternationalizedMessage(null, abe.getMessage());
            }
            return null;
        }
    }

    public String deleteEquipment(Equipment equipment) {
        try{
        equipmentEndpoint.deleteEquipment(equipment);
        return "success";
        }
        catch (AppBaseException abe) {
            Logger.getLogger(EquipmentSession.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji deleteEquipment wyjatku typu: ", abe.getClass());
            if (ContextUtils.isInternationalizationKeyExist(abe.getMessage())) {
                ContextUtils.emitInternationalizedMessage(null, abe.getMessage());
            }
            return null;
        }
    }

    public String saveEquipmentAfterEdit(Equipment equipment) {
        try{
        equipmentEndpoint.saveEquipmentAfterEdit(equipment);
        return "success";
        }
        catch (AppBaseException abe) {
            Logger.getLogger(EquipmentSession.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji saveEquipmentAfterEdit wyjatku typu: ", abe.getClass());
            if (ContextUtils.isInternationalizationKeyExist(abe.getMessage())) {
                ContextUtils.emitInternationalizedMessage(null, abe.getMessage());
            }
            return null;
        }   
    }

    public String addEquipmentComponentToEquipment(EquipmentComponents equipmentComponents) {        
        equipmentEdit = equipmentEndpoint.downloadEquipmentToEdit(equipmentEdit);
        equipmentEndpoint.addEquipmentComponent(equipmentComponents);                      
        return "listEquipmentComponentsFromEquipment";
    }
    
    public String removeEquipmentComponentFromEquipment(EquipmentComponents equipmentComponents)  {        
        equipmentEdit = equipmentEndpoint.downloadEquipmentToEdit(equipmentEdit);
        equipmentEndpoint.removeEquipmentComponentFromEquipment(equipmentComponents);                      
        return "listEquipmentComponentsFromEquipment";
    }

}
