package com.booking.model.Booking_PU;

import com.booking.model.Equipment;
import com.booking.model.EquipmentComponents;
import com.booking.model.Lab;
import com.booking.model.Reservation;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-20T18:02:52")
@StaticMetamodel(Equipment.class)
public class Equipment_ { 

    public static volatile SingularAttribute<Equipment, String> stationNumber;
    public static volatile ListAttribute<Equipment, Reservation> reservations;
    public static volatile SingularAttribute<Equipment, String> stationType;
    public static volatile SingularAttribute<Equipment, Boolean> conditionEquipment;
    public static volatile SingularAttribute<Equipment, Long> id;
    public static volatile ListAttribute<Equipment, EquipmentComponents> equipmentComponents;
    public static volatile SingularAttribute<Equipment, Lab> lab;

}