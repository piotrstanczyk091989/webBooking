package com.booking.ejb.exception;

import com.booking.model.BookingLab;


public class BookingLabException extends AppBaseException {

    static final public String KEY_DUPLICATE = "error.booking.lab.duplicate";
    static final public String KEY_RESERVATION_EQUIPMENT = "error.booking.lab.reservation.equipment.in";
    static final public String KEY_TIME_START_STOP = "error.booking.lab.time";
    
    private BookingLabException(String message) {
        super(message);
    }

    private BookingLabException(String message, Throwable cause) {
        super(message, cause);
    }
    private BookingLab bookingLab;

    public BookingLab getBookingLab() {
        return bookingLab;
    }

    public void setBookingLab(BookingLab bookingLab) {
        this.bookingLab = bookingLab;
    }

    static public BookingLabException createBookingLabExceptionDuplicationBooked(BookingLab bookingLab) {
        BookingLabException be = new BookingLabException(KEY_DUPLICATE);
        be.setBookingLab(bookingLab);
        return be;
    }

    static public BookingLabException createBookingLabExceptionReservationEquipmentInLab(BookingLab bookingLab) {
        BookingLabException be = new BookingLabException(KEY_RESERVATION_EQUIPMENT);
        be.setBookingLab(bookingLab);
        return be;
    }
    
     static public BookingLabException createBookingLabExceptionTimeStartStop(BookingLab bookingLab) {
        BookingLabException be = new BookingLabException(KEY_TIME_START_STOP);
        be.bookingLab=bookingLab;
        return be;
    }
}
