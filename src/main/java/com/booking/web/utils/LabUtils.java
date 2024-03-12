package com.booking.web.utils;

import com.booking.model.Lab;

public class LabUtils {

    public static void rewriteDataAfterEdit(Lab source, Lab target) {
        if (null == source || null == target) {
            return;
        }
        target.setNumber(source.getNumber());
        target.setFloor(source.getFloor());

    }
}
