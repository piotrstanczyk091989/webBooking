package com.booking.web.reservation;

import com.booking.ejb.endpoints.EquipmentEndpoint;
import com.booking.ejb.endpoints.ReservationEndpoint;
import com.booking.ejb.exception.AppBaseException;
import com.booking.model.Equipment;
import com.booking.model.Reservation;
import com.booking.web.utils.ContextUtils;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("reservationSession")
@SessionScoped

public class ReservationSession implements Serializable {

    private static final Logger logger = Logger.getLogger(ReservationSession.class.getSimpleName());
    
    @Inject
    private ReservationEndpoint reservationEndpoint;
 
    @Inject
    private EquipmentEndpoint equipmentEndpoint;
    
    private Reservation reservationEdit;

    private Equipment equipmentEdit;

    public Equipment getEquipmentEdit() {
        return equipmentEdit;
    }
 
    public Reservation getReservationEdit() {
        return reservationEdit;
    }
    
    public List<Reservation> downloadMyReservation() {
        return reservationEndpoint.downloadMyReservation();
    }
    
    public List<Reservation> downloadAllReservation() {
        return reservationEndpoint.downloadAllReservation();
    }
    
    public void confirmReservation(Reservation reservation) {
        reservationEndpoint.confirmReservation(reservation);
    }
    
    public void cancelConfirmReservation(Reservation reservation) {
        reservationEndpoint.cancelConfirmReservation(reservation);
    }
    
    public String deleteReservation(Reservation reservation) {
        try{
        reservationEndpoint.deleteReservation(reservation);
        return "success";
        }
        catch (AppBaseException abe) {
            Logger.getLogger(ReservationSession.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji deleteReservation wyjatku typu: ", abe.getClass());
            if (ContextUtils.isInternationalizationKeyExist(abe.getMessage())) {
                ContextUtils.emitInternationalizedMessage(null, abe.getMessage());
            }
            return null;
        }
    }
        
    public List<Reservation> findReservtionForEquipment(Equipment equipment) {
        return reservationEndpoint.findReservtionForEquipment(equipment);
    }
    
}
