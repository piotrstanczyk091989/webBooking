package com.booking.ejb.exception;

import com.booking.model.EquipmentComponents;


public class EquipmentComponentsException extends AppBaseException {

    static final public String KEY_DB_CONSTRAINT = "error.equipmentComponents.db.constraint.uniq.componentName";
    
    private EquipmentComponentsException(String message) {
        super(message);
    }

    private EquipmentComponentsException(String message, Throwable cause) {
        super(message, cause);
    }
    private EquipmentComponents equipmentComponents;

    public EquipmentComponents getEquipmentComponents() {
        return equipmentComponents;
    }

    static public EquipmentComponentsException createWithDbCheckConstraintKey(EquipmentComponents equipmentComponents, Throwable cause) {
        EquipmentComponentsException ece = new EquipmentComponentsException(KEY_DB_CONSTRAINT, cause);
        ece.equipmentComponents=equipmentComponents;
        return ece;
    }
 
}
