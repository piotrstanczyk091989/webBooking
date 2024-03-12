package com.booking.model.Booking_PU;

import com.booking.model.Equipment;
import com.booking.model.Reservation;
import com.booking.model.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-20T18:02:52")
@StaticMetamodel(Reservation.class)
public class Reservation_ { 

    public static volatile SingularAttribute<Reservation, Equipment> equipmentReservation;
    public static volatile SingularAttribute<Reservation, User> whoMadeReservation;
    public static volatile SingularAttribute<Reservation, Date> lastModification;
    public static volatile SingularAttribute<Reservation, Date> startReservation;
    public static volatile SingularAttribute<Reservation, Long> id;
    public static volatile SingularAttribute<Reservation, Date> stopReservation;
    public static volatile SingularAttribute<Reservation, Boolean> confirmed;

}