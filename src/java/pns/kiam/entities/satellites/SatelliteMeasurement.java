/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.kiam.entities.satellites;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import pns.kiam.entities.satellites.Satellite;
import pns.kiam.entities.telescopes.Telescope;

/**
 *
 * @author User
 */
@Entity
@Table(name = "satelites_measurements")
public class SatelliteMeasurement implements Serializable, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fileAddress = "";
    private long file_id = 0;
    private int ascent_H = 0;
    private int ascent_M = 0;
    private double ascent_S = 0;
    private char mandatory;
    private int decline_H = 0;
    private int decline_M = 0;
    private double decline_S = 0;
    private double accuracy = 0;
    private double brightness = 0;
    private Long date;

    /**
     * one measure on one sat and one telescope
     */
    private int telescopeID;
    private int satID;

    private Satellite satelite;
    private Telescope telescopeList;

    public SatelliteMeasurement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public int getAscent_H() {
        return ascent_H;
    }

    public void setAscent_H(int ascent_H) {
        this.ascent_H = ascent_H;
    }

    public int getAscent_M() {
        return ascent_M;
    }

    public void setAscent_M(int ascent_M) {
        this.ascent_M = ascent_M;
    }

    public double getAscent_S() {
        return ascent_S;
    }

    public void setAscent_S(double ascent_S) {
        this.ascent_S = ascent_S;
    }

    public char getMandatory() {
        return mandatory;
    }

    public void setMandatory(char mandatory) {
        this.mandatory = mandatory;
    }

    public int getDecline_H() {
        return decline_H;
    }

    public void setDecline_H(int decline_H) {
        this.decline_H = decline_H;
    }

    public int getDecline_M() {
        return decline_M;
    }

    public void setDecline_M(int decline_M) {
        this.decline_M = decline_M;
    }

    public double getDecline_S() {
        return decline_S;
    }

    public void setDecline_S(double decline_S) {
        this.decline_S = decline_S;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double getBrightness() {
        return brightness;
    }

    public void setBrightness(double brightness) {
        this.brightness = brightness;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Satellite getSatelite() {
        return satelite;
    }

    public void setSatelite(Satellite satelite) {
        this.satelite = satelite;
    }

    public Telescope getTelescopeList() {
        return telescopeList;
    }

    public void setTelescopeList(Telescope telescopeList) {
        this.telescopeList = telescopeList;
    }

    public long getFile_id() {
        return file_id;
    }

    public void setFile_id(long file_id) {
        this.file_id = file_id;
    }

    public int getTelescopeID() {
        return telescopeID;
    }

    public void setTelescopeID(int telescopeID) {
        this.telescopeID = telescopeID;
    }

    public int getSatID() {
        return satID;
    }

    public void setSatID(int satID) {
        this.satID = satID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SatelliteMeasurement)) {
            return false;
        }
        SatelliteMeasurement other = (SatelliteMeasurement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SatelliteMeasurement{" + "id=" + id + ", fileAddress=" + fileAddress + ", file_id=" + file_id + ", ascent_H=" + ascent_H + ", ascent_M=" + ascent_M + ", ascent_S=" + ascent_S + ", mandatory=" + mandatory + ", decline_H=" + decline_H + ", decline_M=" + decline_M + ", decline_S=" + decline_S + ", accuracy=" + accuracy + ", brightness=" + brightness + ", date=" + date + ", telescopeID=" + telescopeID + ", satID=" + satID + ", satelite=" + satelite + ", telescopeList=" + telescopeList + '}';
    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
