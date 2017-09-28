/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.kiam.Utils;

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
