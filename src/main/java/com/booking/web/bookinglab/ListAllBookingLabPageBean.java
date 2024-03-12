package com.booking.web.bookinglab;

import com.booking.ejb.exception.AppBaseException;
import com.booking.model.BookingLab;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

@Named("listAllBookingLabPageBean")
@RequestScoped
public class ListAllBookingLabPageBean {

    public ListAllBookingLabPageBean() {
    }

    @PostConstruct
    private void init() {
        bookingLab = bookingLabSession.downloadAllBookingLab();
        bookingLabDataModel = new ListDataModel<>(bookingLab);
    }

    @Inject
    private BookingLabSession bookingLabSession;

    private List<BookingLab> bookingLab;
    private DataModel<BookingLab> bookingLabDataModel;

    public DataModel<BookingLab> getBookingLabDataModel() {
        return bookingLabDataModel;
    }

    public void deleteBookingLab(){
        bookingLabSession.deleteBookingLab(bookingLabDataModel.getRowData());
        init();
    }

}
