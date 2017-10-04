/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.kiam.sweb.controllers.telescope;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;
import pns.kiam.entities.telescopes.Telescope;
import pns.kiam.entities.telescopes.TelescopeHorizontMask;
import pns.kiam.sweb.controllers.AbstractController;

/**
 *
 * @author PSEVO tochka
 */
@Stateless
@Named
public class TelescopeListUploaderController extends AbstractController {

    @EJB
    private TelescopeController telescopeController;

    private String fileContent;
    private UploadedFile file;
    private List<String> csvRecords = new ArrayList<>();

    public String getFileContent() {
	return fileContent;
    }

    public void setFileContent(String fileContent) {
	this.fileContent = fileContent;
    }

    public UploadedFile getFile() {
	return file;
    }

    public void setFile(UploadedFile file) {
	this.file = file;
    }

    public void upload() throws IOException {
	if (file != null) {
	    System.out.println(" File: " + file.getFileName());
	    String line = "";
	    try (BufferedReader bir
		    = new BufferedReader(
			    new InputStreamReader(file.getInputstream())
		    )) {
		line = bir.readLine();
		while ((line = bir.readLine()) != null) {
		    count++;
		    line = line.trim();
		    if (line.split(";").length > 0) {
			byte[] b = line.getBytes();
			line = new String(b, "UTF-8");
//			System.out.println(count + "::   " + line);
			telescopeFromProp(line.split(";"));
		    }
		}
	    }
	}
    }

    public void upload01() throws IOException {
	if (file != null) {
	    //fileContent = file.getContents().toString();
	    System.out.println(" File: " + file.getFileName());
	    String line = "";
	    try (BufferedReader bir
		    = new BufferedReader(
			    new InputStreamReader(file.getInputstream())
		    )) {
		line = bir.readLine();

		while ((line = bir.readLine()) != null) {
		    count++;
		    line = line.trim();
//		    fileContent += line + System.lineSeparator();
//		    System.out.println(fileContent);
		    if (count > 2) {
			return;
		    }
		    // System.out.println("   " + line);
		}
//		System.out.println(fileContent);
	    }
	}
    }
    int count = 0;

    private void telescopeFromProp(String[] prop) {
	if (prop.length == 0) {
	    return;
	}
	System.out.println("**");

	System.out.println(count + "                               ");
	System.out.println("***********************");
	System.out.println("");
	Telescope ts = new Telescope();
	TelescopeHorizontMask tr;

	long identifier = (System.currentTimeMillis() / 500 + (int) (222 * Math.random())) % 50001;
	try {
	    identifier = Long.parseLong(prop[2].trim());
	} catch (NumberFormatException e) {
	}
	double x = 0, y = 0, z = 0, height = 0, latitude = 0, longitude = 0;
	latitude = strToDouble(prop[3]);
	longitude = strToDouble(prop[4]);

	x = strToDouble(prop[8]);
	y = strToDouble(prop[9]);
	z = strToDouble(prop[10]);
	height = strToDouble(prop[5]);

	System.out.println("  ******************* count " + count);
	System.out.println("****************************************identifier " + identifier);

	/**
	 * Converting the Angles Data to standard String
	 */
	System.out.println(" propLen " + prop.length);
	for (int k = 11; k < prop.length; k++) {

	    //System.out.println("    " + tn + "  -------------->> tAng " + tAng);
	    try {
		double hor = (15 * (k - 11));
		if (hor <= 360) {// horizont can not be greater then 360 step of formating horizont = 15
		    tr = new TelescopeHorizontMask();

		    double ang = 0;
		    ang = strToDouble(prop[k]);
		    tr.setHorizont(hor);
		    tr.setAngle(ang);
//		    System.out.println(k + ":   ang " + ang + "        ***  prop[k] " + prop[k] + "       *** len " + prop[k].length());
//		    System.out.println(k + ":  Mask " + tr);

		    ts.getTelescopeMask().add(tr);

		    em.persist(tr);
		}
	    } catch (NumberFormatException e) {
	    }
	    //
	}

	ts.setName(prop[0]);
	ts.setLocation(prop[1]);
	ts.setHeight(height);
	ts.setIdentifier(identifier);
	ts.setLongitude(longitude);
	ts.setLatitude(latitude);
	ts.setX(x);
	ts.setY(y);
	ts.setZ(z);

	System.out.println("       Telescope  :  " + ts.toString());
	System.out.println(System.lineSeparator() + "     TelescopeMask: " + ts.getTelescopeMask() + System.lineSeparator() + System.lineSeparator());

	em.persist(ts);
	telescopeController.getTelescopeList().add(ts);
    }

    private double strToDouble(String s) {

	s = s.trim();
	s = s.replace(',', '.');
	if (s.equals("NaN")) {
	    return 0;
	}
	if (s.length() == 0) {
	    return 0;
	}
	try {
	    return Double.parseDouble(s);
	} catch (NumberFormatException e) {
	}
	return 0;
    }

    private void telescopeFromProp(String line) {
	System.out.println("**");

	System.out.println(count + "                               ");
	System.out.println("***********************");
	System.out.println("");

	String[] tProp = line.split(";");
	Telescope ts = new Telescope();
	TelescopeHorizontMask tr;

	if (tProp.length == 0) {
	    return;
	}

	double height = -300;
	long identifier = (System.currentTimeMillis() / 500 + (int) (222 * Math.random())) % 50001;
	double x = 0, y = 0, z = 0, latitude = 0, longitude = 0;

	for (int k = 3; k < 11; k++) {
	    tProp[k] = tProp[k].replace(',', '.');
	}

	try {
	    try {
		identifier = Long.parseLong(tProp[2].trim());
	    } catch (NumberFormatException e) {
//            System.out.println("     ~~~~~~~~~~~~---->>>>>>  tProp[2] " + tProp[2] + "  identifier  " + identifier);
	    }
	    if (identifier == 0) {
		identifier = (System.currentTimeMillis() / 2000 + (int) (222 * Math.random())) % 50001;
		if (identifier < 15000) {
		    identifier += (int) (222 * Math.random()) + 15000;
		}
		identifier = Math.abs(identifier);
	    }

	    try {
		latitude = Double.parseDouble(tProp[3].trim());
	    } catch (NumberFormatException e) {
	    }
	    try {
		longitude = Double.parseDouble(tProp[4].trim());
	    } catch (NumberFormatException e) {
	    }

	    try {
		x = Double.parseDouble(tProp[8].trim());
	    } catch (NumberFormatException e) {
	    }
	    try {
		y = Double.parseDouble(tProp[9].trim());
	    } catch (NumberFormatException e) {
	    }
	    try {
		z = Double.parseDouble(tProp[10].trim());
	    } catch (NumberFormatException e) {
	    }
	    try {
		height = Double.parseDouble(tProp[5].trim());
	    } catch (NumberFormatException e) {
		System.out.println("     ~~~~~~~~~~~~---->>>>>>  tProp[5] " + tProp[5]);
	    }
	} catch (ArrayIndexOutOfBoundsException e) {
	}

	if (count > 145) {
	    System.out.println("------");
	    System.out.println("------");
	    System.out.println("------");
	    System.out.println("------");
	    System.out.println("------");
	    System.out.println("------");
	    //    System.out.println("TR: " + ts.getTelescopeMask());
	    System.out.println("identifier " + identifier);
	    System.out.println("latitude " + latitude);
	    System.out.println("longitude " + longitude);
	    System.out.println(" (X Y Z) (" + x + " " + y + " " + z + ")");
	    System.out.println(" height (" + height);
	    if (count > 146) {
		return;
	    }
	}

	String angles = " ";
	if (tProp.length <= 11) {
	    angles = " ";
	}

	String tAng = " ";
	/**
	 * Converting the Angles Data to standard String
	 */
	for (int k = 11; k < tProp.length; k++) {

	    tAng += "[" + (15 * (k - 11)) + "," + tProp[k] + "];";
	    //System.out.println("    " + tn + "  -------------->> tAng " + tAng);
	    try {
		tr = new TelescopeHorizontMask();
		int ang = Integer.parseInt(tProp[k]);
		Integer.parseInt(tProp[k]);
		tr.setHorizont((15 * (k - 11)));
		tr.setAngle(ang);
//		System.out.println("     Mask  " + tr.toString());
//		save(tr);
		ts.getTelescopeMask().add(tr);
		em.persist(tr);
	    } catch (NumberFormatException e) {
	    }
	    //
	}

//
	try {
	    ts.setName(tProp[0]);
	    ts.setLocation(tProp[1]);
	    ts.setHeight(height);
	    ts.setIdentifier(identifier);
	    ts.setLongitude(longitude);
	    ts.setLatitude(latitude);
	    ts.setX(x);
	    ts.setY(y);
	    ts.setZ(z);
	    System.out.println("       Telescope  :  " + ts.toString());
	    System.out.println(System.lineSeparator() + "     TelescopeMask: " + tAng + System.lineSeparator() + System.lineSeparator());
	} catch (Exception e) {
	    System.err.println(e.getMessage());
	    System.out.println("Error TS: " + ts);
	}
	try {
	    em.persist(ts);
	} catch (Exception e) {
	    System.out.println("");
	    System.err.println("  ERROR Loading " + e.getMessage());
	    System.err.println("*** " + ts);
	    return;
	}
	try {
	    Thread.sleep(30);
	} catch (InterruptedException ex) {
	    //Logger.getLogger(TelescopeConvertControl.class.getName()).log(Level.SEVERE, null, ex);
	}
	System.out.println("**");
	System.out.println("**");
	System.out.println("**");

    }
}
