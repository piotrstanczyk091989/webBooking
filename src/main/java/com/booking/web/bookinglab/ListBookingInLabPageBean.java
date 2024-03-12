package com.booking.web.bookinglab;

import com.booking.ejb.exception.AppBaseException;
import com.booking.model.BookingLab;
import com.booking.model.Lab;
import com.booking.web.lab.LabSession;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

@Named("listBookingInLabPageBean")
@RequestScoped
public class ListBookingInLabPageBean {

    public ListBookingInLabPageBean() {
    }
    
    @PostConstruct
    private void init() {
        lab = labSession.getLabEdit();
        
        bookingLab = bookingLabSession.findBookingInLab(lab);
        bookingLabDataModel = new ListDataModel<>(bookingLab);
    }
    
    private Lab lab = new Lab();

    public Lab getLab() {
        return lab;
    }
    
    @Inject 
    private LabSession labSession;
    
    @Inject
    private BookingLabSession bookingLabSession;
    
    private List<BookingLab> bookingLab;
    private DataModel<BookingLab> bookingLabDataModel;

    public DataModel<BookingLab> getBookingLabDataModel() {
        return bookingLabDataModel;
    }
    
    public String downloadLabToCreateBooking() {
        return labSession.downloadLabToCreateBooking(lab);        
    }
 
}
