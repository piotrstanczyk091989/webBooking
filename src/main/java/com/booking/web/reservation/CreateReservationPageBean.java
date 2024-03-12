package com.booking.web.reservation;

import com.booking.model.Equipment;
import com.booking.model.Reservation;
import com.booking.web.equipment.EquipmentSession;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("createReservationPageBean")
@RequestScoped
public class CreateReservationPageBean implements Serializable {

    public CreateReservationPageBean() {
    }

    @Inject
    private EquipmentSession equipmentSession;

    @Inject
    private ReservationSession reservationSession;

    @PostConstruct
    private void init() {
        equipment = equipmentSession.getEquipmentEdit();
    }

    private Equipment equipment;

    public Equipment getEquipment() {
        return equipment;
    }

    private Reservation reservation = new Reservation();

    public Reservation getReservation() {
        return reservation;
    }

    public String submitReservation() {
        return equipmentSession.createReservationEquipment(reservation);
    }

}
