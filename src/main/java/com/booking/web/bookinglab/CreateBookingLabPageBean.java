package com.booking.web.bookinglab;

import com.booking.web.lab.*;
import com.booking.model.BookingLab;
import com.booking.model.Lab;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("createBookingLabPageBean")
@RequestScoped
public class CreateBookingLabPageBean {

    public CreateBookingLabPageBean() {
    }

    @Inject
    private LabSession labSession;

    @Inject
    private BookingLabSession bookingLabSession;

    @PostConstruct
    private void init() {
        lab = labSession.getLabEdit();
    }

    private Lab lab = new Lab();

    public Lab getLab() {
        return lab;
    }

    private BookingLab bookingLab = new BookingLab();

    public BookingLab getBookingLab() {
        return bookingLab;
    }

    public String createBookingInLab() {
        return labSession.createBookingInLab(bookingLab);
    }

}
