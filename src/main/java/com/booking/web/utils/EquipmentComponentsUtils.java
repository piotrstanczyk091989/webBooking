package com.booking.web.utils;

import com.booking.model.EquipmentComponents;


public class EquipmentComponentsUtils {
    
    public static void rewriteDataAfterEdit(EquipmentComponents source,EquipmentComponents target) {
        if (null == source || null == target) return;
        target.setNameComponent(source.getNameComponent());
        target.setTypeComponent(source.getTypeComponent());        
    }
}
