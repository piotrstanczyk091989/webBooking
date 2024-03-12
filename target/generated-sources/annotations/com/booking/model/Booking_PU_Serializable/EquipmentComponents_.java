package com.booking.model.Booking_PU_Serializable;

import com.booking.model.Equipment;
import com.booking.model.EquipmentComponents;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-20T18:02:52")
@StaticMetamodel(EquipmentComponents.class)
public class EquipmentComponents_ { 

    public static volatile ListAttribute<EquipmentComponents, Equipment> equipments;
    public static volatile SingularAttribute<EquipmentComponents, String> nameComponent;
    public static volatile SingularAttribute<EquipmentComponents, Long> id;
    public static volatile SingularAttribute<EquipmentComponents, String> typeComponent;

}