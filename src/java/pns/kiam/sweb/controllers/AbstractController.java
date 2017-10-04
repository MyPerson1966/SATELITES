/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.kiam.sweb.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

/**
 *
 * @author PSEVO tochka
 */
public class AbstractController {

    @PersistenceContext //(unitName = "S-WEBPU", type = PersistenceContextType.EXTENDED)
    protected EntityManager em;

    protected CriteriaBuilder cb;

    /**
     * Removing the object from DB
     *
     * @param object
     */
    protected void delete(Object object) {
	if (object != null) {

	    System.out.println("  contains " + em.contains(object));

	}
    }

    /**
     * Persisting the NEW object in DB
     *
     * @param object
     */
    protected void persist(Object object) {
	if (object != null) {
	    em.persist(object);
	    em.flush();
	}
    }

    /**
     * Merging the EDITED object to DB
     *
     * @param object
     */
    protected void merge(Object object) {
	if (object != null) {
	    em.merge(object);

	}
    }

}
