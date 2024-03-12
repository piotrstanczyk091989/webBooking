package com.booking.web.lab;

import com.booking.ejb.endpoints.BookingLabEndpoint;
import com.booking.ejb.endpoints.EquipmentEndpoint;
import com.booking.ejb.endpoints.LabEndpoint;
import com.booking.ejb.endpoints.ReservationEndpoint;
import com.booking.ejb.exception.AppBaseException;
import com.booking.ejb.exception.BookingLabException;
import com.booking.ejb.exception.LabException;
import com.booking.model.BookingLab;
import com.booking.model.Equipment;
import com.booking.model.Lab;
import com.booking.model.Reservation;
import com.booking.web.utils.ContextUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("labSession")
@SessionScoped
public class LabSession implements Serializable {

    private static final Logger logger = Logger.getLogger(LabSession.class.getSimpleName());

    @Inject
    private LabEndpoint labEndpoint;

    @Inject
    private EquipmentEndpoint equipmentEndpoint;

    @Inject
    private BookingLabEndpoint bookingLabEndpoint;

    @Inject
    private ReservationEndpoint reservationEndpoint;

    private Equipment equipmentEdit;

    private Lab labEdit;

    private Lab labDelete;

    public Lab getLabEdit() {
        return labEdit;
    }

    public Lab getLabDelete() {
        return labDelete;
    }

    public Equipment getEquipmentEdit() {
        return equipmentEdit;
    }

    public String createEquipmentInLab(Equipment equipment) {
        try {
            labEdit.getEquipmentsInLab().add(equipment);
            equipment.setLab(labEdit);
            equipmentEndpoint.createEquipment(equipment);
            labEndpoint.saveLabAfterEdit(labEdit);
            return "listEquipmentInLab";
        } catch (AppBaseException abe) {
            Logger.getLogger(LabSession.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji createReservationEquipment wyjatku typu: ", abe.getClass());
            if (ContextUtils.isInternationalizationKeyExist(abe.getMessage())) {
                ContextUtils.emitInternationalizedMessage(null, abe.getMessage());
            }
            return null;
        }
    }

    public String createBookingInLab(BookingLab bookingLab) {
        List<BookingLab> bl = bookingLabEndpoint.findBookingInLab(labEdit.getNumber());
        Date startB = bookingLab.getStartBookingLab();
        Date stopB = bookingLab.getStopBookingLab();

        List<Reservation> res = reservationEndpoint.downloadAllReservation();
        Date startR = bookingLab.getStartBookingLab();
        Date stopR = bookingLab.getStopBookingLab();

        try {
            if (startR.after(stopR)) {
                throw BookingLabException.createBookingLabExceptionTimeStartStop(bookingLab);
            }
            for (BookingLab b : bl) {
                if (b.getStartBookingLab().after(startB) && b.getStartBookingLab().after(stopB)); else if (b.getStopBookingLab().before(startB) && b.getStopBookingLab().before(stopB)); else {
                    throw BookingLabException.createBookingLabExceptionDuplicationBooked(bookingLab);
                }
            }

            for (Reservation b : res) {
                if (b.getStartReservation().after(startR) && b.getStartReservation().after(stopR)); else if (b.getStopReservation().before(startR) && b.getStopReservation().before(stopR)); else {
                    throw BookingLabException.createBookingLabExceptionReservationEquipmentInLab(bookingLab);
                }
            }

            labEdit.getReservations().add(bookingLab);
            bookingLab.setLabReservation(labEdit);
            bookingLabEndpoint.whoAddBookingLab(bookingLab);
            return "listBookingInLab";
        } catch (BookingLabException be) {

            if (BookingLabException.KEY_DUPLICATE.equals(be.getMessage())) {
                ContextUtils.emitInternationalizedMessage("msgs", BookingLabException.KEY_DUPLICATE);
            } else if (BookingLabException.KEY_RESERVATION_EQUIPMENT.equals(be.getMessage())) {
                ContextUtils.emitInternationalizedMessage("msgs", BookingLabException.KEY_RESERVATION_EQUIPMENT);
            } else if (BookingLabException.KEY_TIME_START_STOP.equals(be.getMessage())) {
                ContextUtils.emitInternationalizedMessage("msgs", BookingLabException.KEY_TIME_START_STOP);
            } else {
                Logger.getLogger(LabSession.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji createReservationEquipment wyjatku: ", be);
            }
            return null;
        } catch (AppBaseException abe) {
            Logger.getLogger(LabSession.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji createReservationEquipment wyjatku typu: ", abe.getClass());
            if (ContextUtils.isInternationalizationKeyExist(abe.getMessage())) {
                ContextUtils.emitInternationalizedMessage(null, abe.getMessage());
            }
            return null;
        }        
    }

    public List<Lab> findAllLab() {
        return labEndpoint.downloadAllLab();
    }

    public String downloadLabToEdit(Lab lab) {
        labEdit = labEndpoint.downloadLabToEdit(lab);
        return "editLab";
    }

    public String listEquipmentInLab(Lab lab) {
        labEdit = labEndpoint.downloadLabToEdit(lab);
        return "listEquipmentInLab";
    }

    public String listBookingInLab(Lab lab) {
        labEdit = labEndpoint.downloadLabToEdit(lab);
        return "listBookingInLab";
    }

    public String downloadLabToCreateEquipmentIn(Lab lab) {
        labEdit = labEndpoint.downloadLabToEdit(lab);
        return "createEquipmentInLab";
    }

    public String downloadLabToCreateBooking(Lab lab) {
        labEdit = labEndpoint.downloadLabToEdit(lab);
        return "createBookingLab";
    }

    public String downloadLabToDelete(Lab lab) {
        labDelete = labEndpoint.downloadLabToEdit(lab);
        return "deleteLab";
    }

    public String createLab(Lab lab) {
        try {
            labEndpoint.createLab(lab);
            return "success";
        } catch (LabException ke) {
            if (LabException.KEY_DB_CONSTRAINT.equals(ke.getMessage())) {
                ContextUtils.emitInternationalizedMessage("number", LabException.KEY_DB_CONSTRAINT);
            } else {
                Logger.getLogger(LabException.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji createLab wyjatku: ", ke);
            }
            return null;
        } catch (AppBaseException abe) {
            Logger.getLogger(LabSession.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji createLab wyjatku typu: ", abe.getClass());
            if (ContextUtils.isInternationalizationKeyExist(abe.getMessage())) {
                ContextUtils.emitInternationalizedMessage(null, abe.getMessage());
            }
            return null;
        }
    }

    public String deleteLab(Lab lab) {
        try {
            labEndpoint.deleteLab(lab);
            return "success";
        } catch (LabException ke) {
            if (LabException.KEY_DB_REMOVE.equals(ke.getMessage())) {
                ContextUtils.emitInternationalizedMessage("deleteLabForm", LabException.KEY_DB_REMOVE);
            } else {
                Logger.getLogger(LabException.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji deleteLab wyjatku: ", ke);
            }
            return null;
        } catch (AppBaseException abe) {
            Logger.getLogger(LabSession.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji createLab wyjatku typu: ", abe.getClass());
            if (ContextUtils.isInternationalizationKeyExist(abe.getMessage())) {
                ContextUtils.emitInternationalizedMessage(null, abe.getMessage());
            }
            return null;
        }
    }

    public String saveLabAfterEdit(Lab lab) {
        try {
            labEndpoint.saveLabAfterEdit(lab);
            return "success";
        } catch (LabException ke) {
            if (LabException.KEY_DB_CONSTRAINT.equals(ke.getMessage())) {
                ContextUtils.emitInternationalizedMessage("number", LabException.KEY_DB_CONSTRAINT);
            } else {
                Logger.getLogger(LabException.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji createLab wyjatku: ", ke);
            }
            return null;
        } catch (AppBaseException abe) {
            Logger.getLogger(LabSession.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji createLab wyjatku typu: ", abe.getClass());
            if (ContextUtils.isInternationalizationKeyExist(abe.getMessage())) {
                ContextUtils.emitInternationalizedMessage(null, abe.getMessage());
            }
            return null;
        }
    }

}
