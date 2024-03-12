package com.booking.model.Booking_PU;

import com.booking.model.BookingLab;
import com.booking.model.Equipment;
import com.booking.model.Lab;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-20T18:02:52")
@StaticMetamodel(Lab.class)
public class Lab_ { 

    public static volatile SingularAttribute<Lab, String> number;
    public static volatile ListAttribute<Lab, Equipment> equipmentsInLab;
    public static volatile ListAttribute<Lab, BookingLab> reservations;
    public static volatile SingularAttribute<Lab, Long> id;
    public static volatile SingularAttribute<Lab, String> floor;

}