package com.booking.web.reservation;

import com.booking.ejb.exception.AppBaseException;
import com.booking.model.Reservation;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

@Named("listReservationPageBean")
@RequestScoped
public class ListReservationPageBean {

    public ListReservationPageBean() {
    }

    @PostConstruct
    private void init() {
        reservations = reservationSession.downloadAllReservation();
        reservationsDataModel = new ListDataModel<>(reservations);
    }

    @Inject
    private ReservationSession reservationSession;

    private List<Reservation> reservations;
    private DataModel<Reservation> reservationsDataModel;

    public DataModel<Reservation> getReservationsDataModel() {
        return reservationsDataModel;
    }

    public void confirmReservation() {
        reservationSession.confirmReservation(reservationsDataModel.getRowData());
        init();
    }

    public void cancelConfirmReservation() {
        reservationSession.cancelConfirmReservation(reservationsDataModel.getRowData());
        init();
    }

    public void deleteReservation() {
        reservationSession.deleteReservation(reservationsDataModel.getRowData());
        init();
    }

}
