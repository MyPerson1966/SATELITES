/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.kiam.sweb.controllers.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.primefaces.event.RowEditEvent;
import pns.kiam.entities.users.UserType;
import pns.kiam.sweb.controllers.AbstractController;
import pns.kiam.sweb.utils.MessageUtils;

/**
 *
 * @author PSEVO tochka
 */
@Stateless
@Named
public class UserTypeController extends AbstractController implements Serializable {

    private UserType userType;
    private List<UserType> userTypeList = new ArrayList<>();
    private CriteriaQuery<UserType> cq;

    @PostConstruct
    public void init() {
	try {
	    cb = em.getCriteriaBuilder();
	    cq = cb.createQuery(UserType.class);
	    userTypeList = loadAllTypes();
	} catch (NullPointerException e) {
	}

    }

    private List loadAllTypes() {

	Root<UserType> res = cq.from(UserType.class);
	cq.select(res);
	cq.orderBy(cb.asc(res.get("id")));
	TypedQuery<UserType> Q = em.createQuery(cq);
	System.out.println(" " + Q.getResultList().size());

	(new MessageUtils()).messageGenerator(" User Type Number is: " + Q.getResultList().size(), "");
	return Q.getResultList();
    }

    public UserType getUserType() {
	return userType;
    }

    public void setUserType(UserType userType) {
	this.userType = userType;
    }

    public List<UserType> getUserTypeList() {
	return userTypeList;
    }

    public void setUserTypeList(List<UserType> userTypeList) {
	this.userTypeList = userTypeList;
    }

    /**
     * Row Select action
     */
    public void rowSelect() {

    }

    /**
     * Row Select action by given Telescope
     *
     * @param t
     */
    public void rowSelectAction(UserType t) {
	userType = t;
	(new MessageUtils()).messageGenerator("User Type Selected", userType.toString());
	System.out.println("  Selected " + t);
    }

    /**
     * Prepare creation of user Type
     */
    public void prepareCreation() {
	userType = new UserType();
	//userType.setRights(8);
	//userType.setName("Super User");
//	System.out.println("  " + userType);
	userTypeList.add(userType);

	(new MessageUtils()).messageGenerator("Prepare Creating A New User Type ", "");
	rowDeSelect();
    }

    /**
     * Editing Table's row
     *
     * @param event
     */
    public void onRowEdit(RowEditEvent event) {
	userType = (UserType) event.getObject();
	System.out.println(">>>>>>>" + userType);
	if (userType.getId() == null) {
	    em.persist(userType);
	} else {
	    em.merge(userType);
	}
	rowDeSelect();
	(new MessageUtils()).messageGenerator("User Type Edited", ((UserType) event.getObject()).toString());
    }

    /**
     * Cancelling edit a row. Setting up a selection as null
     *
     * @param event
     */
    public void onRowCancel(RowEditEvent event) {
	userType = null;
	(new MessageUtils()).messageGenerator("Edit Cancelled ", ((UserType) event.getObject()).toString());
    }

    /**
     * Deselect the Selected Row
     */
    public void rowDeSelect() {
	userType = null;
    }

    public void removeRow(boolean all) {
	System.out.println("    REMOVE ALL " + all);
	if (all) {
	    deleteUserType();
	} else {
	    if (userType != null) {
		deleteUserType(userType.getId());
	    }
	}
	init();
    }

    private void deleteUserType(long id) {
	UserType uTmp = em.find(UserType.class, id);
	em.remove(uTmp);
    }

    private void deleteUserType() {
	for (int k = 0; k < userTypeList.size(); k++) {
	    UserType tmp = userTypeList.get(k);
	    deleteUserType(tmp.getId());
	}
    }
}
