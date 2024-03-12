package com.booking.ejb.endpoints;

import com.booking.ejb.exception.AppBaseException;
import com.booking.ejb.facades.ReservationFacade;
import com.booking.ejb.interceptor.LoggingInterceptor;
import com.booking.model.Account;
import com.booking.model.Equipment;
import com.booking.model.Reservation;
import com.booking.model.User;
import com.booking.web.utils.AccountUtils;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

@Stateful
@LocalBean
@Interceptors(LoggingInterceptor.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@RolesAllowed({"Uzytkownik", "Administrator"})
public class ReservationEndpoint extends AbstractEndpoint implements SessionSynchronization {

    @Inject
    private AccountEndpoint accountEndpoint;

    @Inject
    private ReservationFacade reservationFacade;

    @RolesAllowed("Uzytkownik")
    public void createReservation(Reservation reservation) throws AppBaseException {
        reservationFacade.create(reservation);
    }

    public List<Reservation> downloadMyReservation() {
        return reservationFacade.findReservationForUser(accountEndpoint.downloadMyLogin());
    }

    public List<Reservation> downloadAllReservation() {
        return reservationFacade.findAllReservtion();
    }

    public void confirmReservation(Reservation reservation) {
        Reservation r = reservationFacade.find(reservation.getId());
        r.setConfirmed(true);
    }

    @RolesAllowed("Administrator")
    public void cancelConfirmReservation(Reservation reservation) {
        Reservation r = reservationFacade.find(reservation.getId());
        r.setConfirmed(false);
    }

    public void deleteReservation(Reservation reservation) throws AppBaseException {
        reservationFacade.remove(reservation);
    }

    public void whoAddReservation(Reservation reservation) {

        Account myAccount = accountEndpoint.downloadMyAccount();

        if (!(AccountUtils.isUser(myAccount))) {
            throw new IllegalArgumentException("Tylko użytkowni może składać nowe reservation");
        }
        User myAccountUser = (User) myAccount;
        reservation.setWhoMade(myAccountUser);
        myAccountUser.getReservations().add(reservation);
    }

    public List<Reservation> findReservtionForEquipment(Equipment equipment) {
        Long id = equipment.getId();
        return reservationFacade.findReservtionForEquipment(id);
    }

}
