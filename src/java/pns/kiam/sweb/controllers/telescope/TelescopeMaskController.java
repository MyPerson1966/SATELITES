/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.kiam.sweb.controllers.telescope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import pns.kiam.Utils.NumberUtils;
import pns.kiam.entities.telescopes.Telescope;
import pns.kiam.entities.telescopes.TelescopeHorizontMask;
import pns.kiam.sweb.controllers.AbstractController;
import pns.kiam.sweb.utils.MessageUtils;

/**
 *
 * @author PSEVO tochka
 */
@Named
@Stateless

public class TelescopeMaskController extends AbstractController implements Serializable {

    /**
     * angle >=0 && <=90  step=1
     * horizont >=0 && <=360 step = 15
     */
//    @EJB
    private TelescopeController telescopeController;

    private TelescopeHorizontMask horizontMask;
    private Telescope telescope;
    private double[] angleRange;//=new double[91];
    private double[] horizontRange;// =new double[25];
    private List<Double> usedHorizonts = new ArrayList<>();

    private String selectedInfo = "";
    private boolean editModeActive = false;

    @PostConstruct
    public void init() {
	initTelescopeMask();
    }

//    private void testExclusion() {
//	List<Double> an = NumberUtils.arrayToList(angleRange);
//	List<Double> hr = NumberUtils.arrayToList(horizontRange);
//	List<Double> test = NumberUtils.excludefromList(hr, an);
//
//	System.out.println("    INPUTS  ");
//	System.out.println("");
//	System.out.print("From hr   " + hr.size());
//	for (int i = 0; i < hr.size(); i++) {
//	    System.out.print(" " + hr.get(i) + "; ");
//	}
//	System.out.println("");
//	System.out.println("exclude data  an   ");
//	for (int i = 0; i < an.size(); i++) {
//	    System.out.print(" " + an.get(i) + "; ");
//	}
//
//	System.out.println("");
//	System.out.println("result  test   ");
//	for (int i = 0; i < test.size(); i++) {
//	    System.out.print(" " + test.get(i) + "; ");
//	}
//
//    }
    public boolean isEditModeActive() {
	return editModeActive;
    }

    public String getSelectedInfo() {
	return selectedInfo;
    }

    public void setSelectedInfo(String selectedInfo) {
	this.selectedInfo = selectedInfo;
    }

    public TelescopeHorizontMask getHorizontMask() {
	return horizontMask;
    }

    public void setHorizontMask(TelescopeHorizontMask horizontMask) {
	this.horizontMask = horizontMask;
    }

    public Telescope getTelescope() {
	return telescope;
    }

    public void setTelescope(Telescope telescope) {
	this.telescope = telescope;
	fullRefreshTelescopeMask();

    }

    public double[] getAngleRange() {
	return angleRange;
    }

    public double[] getHorizontRange() {
	return horizontRange;
    }

    public void rowSelect(SelectEvent event) {
	horizontMask = (TelescopeHorizontMask) event.getObject();
	System.out.println("Mask Selected " + horizontMask.getId() + "    " + horizontMask + "  " + horizontMask.getTmpId());
    }

    public void rowSelectAction(TelescopeHorizontMask t) {
	horizontMask = t;

	selectedInfo = "Selected Mask " + horizontMask.toString();

	(new MessageUtils()).messageGenerator("Telescope Mask Selected", horizontMask.toString());
	System.out.println("  Selected " + t + "   " + horizontMask.getId() + "   " + em.contains(horizontMask));
    }

    public void prepareCreate() {
	TelescopeHorizontMask thm = new TelescopeHorizontMask();

	telescope.getTelescopeMask().add(thm);
	System.out.println("thm" + thm + "   " + telescope.getTelescopeMask().size());
	rowDeSelect();
	editModeActive = true;
    }

    public void horizontChange(ValueChangeEvent event) {
	System.out.println("  Changed horizont " + event.getNewValue());

    }

    public void onRowEdit(RowEditEvent event) {
	TelescopeHorizontMask mask = (TelescopeHorizontMask) event.getObject();

	// Join the telescope to the mask to provide complete fixing mask
	mask.setTelescope(telescope);
	System.out.println("Before PERSISTING " + mask.getId());

	if (mask.getId() == null) {
	    persist(mask);// After this the entity mask fixies in the DB with  telescopeID
	} else {
	    merge(mask);
	}

	System.out.println("After PERSISTING " + mask.getId());

	fullRefreshTelescopeMask();
	editModeActive = false;
	(new MessageUtils()).messageGenerator("Telescope Mask Editeded. Result is: ", mask.toString());

	rowDeSelect();
    }

    public void rowDeSelect() {
	horizontMask = null;
	selectedInfo = "";
	(new MessageUtils()).messageGenerator("Telescope Mask Deselected  ", "...");

    }

    /**
     * Removes the record(s) from Telescope table. if the "all " parameter is
     * true, removes all records else it is false removes only selected record
     * The represenation data in the lisr remove correspondently
     *
     * @param all
     */
    public void removeRow(boolean all) {
	if (all) {
	    if (telescope != null) {
		for (int k = 0; k < telescope.getTelescopeMask().size(); k++) {
		    deleteMask(telescope.getTelescopeMask().get(k).getId());
		    //System.out.println(k + " REMOVE TEST: removing of  " + " " + telescope.getTelescopeMask().get(k).getId());;
		}
	    }
	    telescope.getTelescopeMask().clear();
	    (new MessageUtils()).messageGenerator("Telescope " + telescope.getIdentifier(), "All masks removes");
	}
	if (!all && horizontMask != null) {
	    System.out.println("MASK TO DELETE   " + horizontMask + ";    " + horizontMask.getId() + ";   " + horizontMask.getClass().getCanonicalName());
	    deleteMask(horizontMask.getId());
	    telescope.getTelescopeMask().remove(horizontMask);
	    (new MessageUtils()).messageGenerator("Telescope " + telescope.getIdentifier(), "The mask " + horizontMask.toString() + " removes");
	}
	setTelescope(telescope);
//	fullRefreshTelescopeMask();
//	reduceHorizonts();
	horizontMask = null;
	rowDeSelect();
    }

    private void deleteMask(long id) {
	TelescopeHorizontMask tshm = em.find(TelescopeHorizontMask.class, id);
	System.out.println("ID " + id + "    tshm " + tshm);

	if (tshm != null) {
	    em.remove(tshm);
	}
    }

    private void deleteMask(TelescopeHorizontMask tshm) {
//	em.merge(tshm);
	System.out.println("Remove TSHM " + tshm.getId() + "  " + tshm);
	if (tshm != null) {
	    em.remove(tshm);
	}
//	System.out.println("  EM has Mask " + em.contains(tshm));
//	em.merge(tshm);
//	System.out.println("  EM has Mask " + em.contains(tshm));
    }

    private void refreshHorizontList() {
	if (telescope != null) {
	    usedHorizonts.clear();
	    for (int k = 0; k < telescope.getTelescopeMask().size(); k++) {
		usedHorizonts.add(telescope.getTelescopeMask().get(k).getHorizont());
	    }
	}
	reduceHorizonts();
    }

    private void reduceHorizonts() {
	initTelescopeMask();
	double[] horizontRange11 = NumberUtils.excludefromList(horizontRange, usedHorizonts);
	System.out.println("  New Rang  " + horizontRange11.length);
	horizontRange = NumberUtils.copyArrayToArray(horizontRange11);
    }

    private void initTelescopeMask() {
	angleRange = NumberUtils.generateDoubleArray(0, 90, 1);
	//angleRange = TelescopeUtils.generateDoubleArray(0, 20, 4);
	horizontRange = NumberUtils.generateDoubleArray(0, 360, 15);
	//horizontRange = TelescopeUtils.generateDoubleArray(0, 40, 2);

    }

    private void fullRefreshTelescopeMask() {
	initTelescopeMask();
	refreshHorizontList();
    }

    private boolean displayGivenHorizont(double h) {
	return usedHorizonts.contains(h);
    }

}
