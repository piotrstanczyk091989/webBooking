package com.booking.ejb.exception;

import com.booking.model.Reservation;


public class ReservationException extends AppBaseException {

    static final public String KEY_DUPLICATE = "error.reservation.equipment.duplikate";
    static final public String KEY_BOOKED_LAB = "error.reservation.lab.booking";
    static final public String KEY_TIME_START_STOP = "error.reservation.time";
    
    
    private ReservationException(String message) {
        super(message);
    }

    private ReservationException(String message, Throwable cause) {
        super(message, cause);
    }
    private Reservation reservation;

    public Reservation getReservation() {
        return reservation;
    }


    static public ReservationException createReservationExceptionDuplicateReservationEquipment(Reservation reservation) {
        ReservationException re = new ReservationException(KEY_DUPLICATE);
        re.reservation=reservation;
        return re;
    }
    
     static public ReservationException createReservationExceptionBookedLab(Reservation reservation) {
        ReservationException re = new ReservationException(KEY_BOOKED_LAB);
        re.reservation=reservation;
        return re;
    }
    
     static public ReservationException createReservationExceptionTimeStartStop(Reservation reservation) {
        ReservationException re = new ReservationException(KEY_TIME_START_STOP);
        re.reservation=reservation;
        return re;
    }
 
}
