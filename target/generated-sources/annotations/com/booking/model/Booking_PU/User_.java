package com.booking.model.Booking_PU;

import com.booking.model.Reservation;
import com.booking.model.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-20T18:02:52")
@StaticMetamodel(User.class)
public class User_ extends Account_ {

    public static volatile SingularAttribute<User, String> nip;
    public static volatile ListAttribute<User, Reservation> reservations;

}