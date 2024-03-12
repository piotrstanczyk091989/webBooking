package com.booking.model.Booking_PU;

import com.booking.model.Administrator;
import com.booking.model.BookingLab;
import com.booking.model.Lab;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-20T18:02:52")
@StaticMetamodel(BookingLab.class)
public class BookingLab_ { 

    public static volatile SingularAttribute<BookingLab, Date> startBookingLab;
    public static volatile SingularAttribute<BookingLab, Date> lastModification;
    public static volatile SingularAttribute<BookingLab, Lab> labReservation;
    public static volatile SingularAttribute<BookingLab, String> description;
    public static volatile SingularAttribute<BookingLab, Long> id;
    public static volatile SingularAttribute<BookingLab, Boolean> confirmed;
    public static volatile SingularAttribute<BookingLab, Administrator> whoMade;
    public static volatile SingularAttribute<BookingLab, Date> stopBookingLab;

}