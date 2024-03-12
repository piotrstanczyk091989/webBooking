package com.booking.model.Booking_PU;

import com.booking.model.Administrator;
import com.booking.model.BookingLab;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-20T18:02:52")
@StaticMetamodel(Administrator.class)
public class Administrator_ extends Account_ {

    public static volatile ListAttribute<Administrator, BookingLab> booking;
    public static volatile SingularAttribute<Administrator, String> alarmNumber;

}