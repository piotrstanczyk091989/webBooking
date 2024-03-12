package com.booking.web.utils;

import com.booking.model.Account;
import com.booking.model.Administrator;
import com.booking.model.User;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

public class AccountUtils {

    public static boolean isAdministrator(Account account) {
        return (account instanceof Administrator);
    }

    public static boolean isUser(Account account) {
        return (account instanceof User);
    }

    public static void rewriteDataAfterEdit(Account source, Account target) {

        if (null == source || null == target) {
            return;
        }

        target.setName(source.getName());
        target.setSurname(source.getSurname());
        target.setEmail(source.getEmail());
        target.setPhone(source.getPhone());

        if (isAdministrator(source) && isAdministrator(target)) {
            Administrator sourceAdministrator = (Administrator) source;
            Administrator targetAdministrator = (Administrator) target;
            targetAdministrator.setAlarmNumber(sourceAdministrator.getAlarmNumber());
        }

        if (isUser(source) && isUser(target)) {
            User sourceUser = (User) source;
            User targetUser = (User) target;
            targetUser.setNip(sourceUser.getNip());
        }
    }

    public static String computeDigestPassword(String publicPassword) throws NoSuchAlgorithmException {
        //TODO: wstawić algorytm skrótu hasła
        MessageDigest mDigest = MessageDigest.getInstance("MD5");
        byte[] result = mDigest.digest(publicPassword.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        System.out.println(sb.toString() + "  to haslo");
        return sb.toString();
    }

}
