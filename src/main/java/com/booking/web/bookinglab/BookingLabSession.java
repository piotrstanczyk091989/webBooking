package com.booking.web.bookinglab;

import com.booking.ejb.endpoints.BookingLabEndpoint;
import com.booking.ejb.endpoints.LabEndpoint;
import com.booking.ejb.exception.AppBaseException;
import com.booking.model.BookingLab;
import com.booking.model.Lab;
import com.booking.web.utils.ContextUtils;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("bookingLabSession")
@SessionScoped
public class BookingLabSession implements Serializable {

    private static final Logger logger = Logger.getLogger(BookingLabSession.class.getSimpleName());

    @Inject
    private BookingLabEndpoint bookingLabEndpoint;

    @Inject
    private LabEndpoint labEndpoint;

    private BookingLab bookingLabEdit;

    private Lab labEdit;

    public Lab getLabEdit() {
        return labEdit;
    }

    public List<BookingLab> findBookingInLab(Lab lab) {
        labEdit = labEndpoint.downloadLabToEdit(lab);
        return bookingLabEndpoint.findBookingInLab(labEdit.getNumber());
    }

    public BookingLab getBookingLabEdit() {
        return bookingLabEdit;
    }

    public List<BookingLab> downloadAllBookingLab() {
        return bookingLabEndpoint.downloadAllBookingLab();
    }

    public String deleteBookingLab(BookingLab bookingLab) {
        try {
            bookingLabEndpoint.deleteBookingLab(bookingLab);
            return "success";
        } catch (AppBaseException abe) {
            Logger.getLogger(BookingLabSession.class.getName()).log(Level.SEVERE, "Zgłoszenie w metodzie akcji deleteBookingLab wyjatku typu: ", abe.getClass());
            if (ContextUtils.isInternationalizationKeyExist(abe.getMessage())) {
                ContextUtils.emitInternationalizedMessage(null, abe.getMessage());
            }
            return null;
        }
    }
}
