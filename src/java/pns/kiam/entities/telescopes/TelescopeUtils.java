/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.kiam.entities.telescopes;

import javax.persistence.EntityManager;
import pns.kiam.entities.telescopes.Telescope;
import pns.kiam.entities.telescopes.TelescopeHorizontMask;

/**
 *
 * @author PSEVO tochka
 */
public class TelescopeUtils {

    public static void deAttachTelescopeMasks(Telescope t, EntityManager em) {
	for (int k = 0; k < t.getTelescopeMask().size(); k++) {
	    if (em.contains(t.getTelescopeMask().get(k))) {
		em.detach(t.getTelescopeMask().get(k));
	    }
	}
    }

    public static boolean maskInUse(Telescope t, double angle, double horizont) {
	for (TelescopeHorizontMask mask : t.getTelescopeMask()) {
	    boolean a = (angle == mask.getAngle());
	    boolean h = (horizont == mask.getHorizont());
	    if (a && h) {
		return true;
	    }
	}

	return false;
    }

}
