/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.kiam.Utils;

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
