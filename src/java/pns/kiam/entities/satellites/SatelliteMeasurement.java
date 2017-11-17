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

    private int ascent_H = 0;
    private int ascent_M = 0;
    private int ascent_S = 0;
    private char mandatory;
    private int decline_H = 0;
    private int decline_M = 0;
    private int decline_S = 0;
    private double accuracy = 0;
    private double brightness = 0;

    private Long date;

    /**
     * one measure on one sat and one telescope
     */
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
        return "pns.kiam.entities.oservatories.SateliteMeasurement[ id=" + id + " ]";
    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
