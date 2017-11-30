/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.kiam.Utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import pns.kiam.entities.telescopes.Telescope;

/**
 *
 * @author User
 */
public class StringUtils {

    public static String telescopeHorizontMaskAsString(Telescope t) {
        String res = " ";
        if (t != null) {
            for (int k = 0; k < t.getTelescopeMask().size(); k++) {
                res += t.getTelescopeMask().get(k) + " ";
            }
        }

        return res;
    }

    /**
     * Converting a string a pare of strings sDate, having a type DDMMYY and
     * sTime, having a type HHMMSSSS to a date value as Date representation.
     *
     * @param sDate, sTime
     * @return
     */
    public static Date ddmmyyHHMMSSSSSToDate(String sDate, String sTime) {
        SimpleDateFormat f = new SimpleDateFormat("ddMMyyHHmmssSSS");

        Date d;
        try {
            d = f.parse(sDate + sTime);
            return d;
        } catch (ParseException ex) {
        }
        return new Date(2);
    }

    /**
     * Converting a string a pare of strings sDate, having a type DDMMYY and
     * sTime, having a type HHMMSSSS to a long value as Date representation.
     *
     * @param sDate, sTime
     * @return
     */
    public static long ddmmyyHHMMSSSSSToLong(String sDate, String sTime) {
        Date d = ddmmyyHHMMSSSSSToDate(sDate, sTime);
        return d.getTime();
    }

    /**
     * creating ascension from HHMMSSSS string format
     *
     * @param sTime
     */
    private static void ascensionCreate(String sTime) {
        SimpleDateFormat f = new SimpleDateFormat("HHmmssSSS");
        Date d;
        try {
            d = f.parse(sTime);
            System.out.println(" d= " + d + "  " + d.getTime());
        } catch (ParseException ex) {
            //Logger.getLogger(TestStringToDate.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static String md5Maker(String s) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        s = pns.utils.strings.RStrings.removeSpaces(s);

        byte[] hash = digest.digest(s.getBytes(StandardCharsets.UTF_8));
        return hexStrVal(hash);
    }

    public static String hexStrVal(byte[] bts) {
        StringBuffer sbf = new StringBuffer();

        for (byte b : bts) {
            long ti = b & 0xff;
            sbf.append(Long.toHexString(ti));
        }
        return sbf.toString();
    }

    /**
     * Generate a randomLong INTString
     *
     * @param L
     * @return
     */
    public String longString(String L) {
        if (L == null) {
            L = "0";
        }
        return NumberUtils.genRandomSuperBigInt() + L;
    }
}
