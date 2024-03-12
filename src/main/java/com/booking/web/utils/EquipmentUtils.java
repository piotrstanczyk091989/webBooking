package com.booking.web.utils;

import com.booking.model.Equipment;

public class EquipmentUtils {

    public static void rewriteDataAfterEdit(Equipment source, Equipment target) {
        if (null == source || null == target) {
            return;
        }
        target.setStationNumber(source.getStationNumber());
        target.setStationType(source.getStationType());

    }

    public static void rewriteDataAfterReservation(Equipment source, Equipment target) {
        if (null == source || null == target) {
            return;
        }
        target.setReservations(source.getReservations());

    }
}
