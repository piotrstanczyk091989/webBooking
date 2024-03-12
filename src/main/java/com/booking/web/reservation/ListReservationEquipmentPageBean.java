package com.booking.web.reservation;

import com.booking.model.Equipment;
import com.booking.model.Reservation;
import com.booking.web.equipment.EquipmentSession;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

@Named("listReservationEquipmentPageBean")
@RequestScoped
public class ListReservationEquipmentPageBean {

    public ListReservationEquipmentPageBean() {
    }

    @Inject
    private EquipmentSession equipmentSession;

    @Inject
    private ReservationSession reservationSession;

    @PostConstruct
    private void init() {
        equipment = equipmentSession.getEquipmentEdit();
        reservationList = reservationSession.findReservtionForEquipment(equipment);
        reservationDataModel = new ListDataModel<>(reservationList);
    }

    private Equipment equipment = new Equipment();

    public Equipment getEquipment() {
        return equipment;
    }

    private List<Reservation> reservationList;
    private DataModel<Reservation> reservationDataModel;

    public DataModel<Reservation> getReservationDataModel() {
        return reservationDataModel;
    }

}
