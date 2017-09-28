/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.kiam.entities.telescopes;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 *
 * @author User
 */
@Entity
@Table(name = "TelescopeMasks",
	uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"horizont", "angle", "telescope_id"})}
)
public class TelescopeHorizontMask implements Serializable, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(0)
    @Max(90)
    private double angle;

    @Min(0)
    @Max(360)
    private double horizont;

    @ManyToOne
    @JoinColumn(name = "telescope_id")
    private Telescope telescope;

    @Transient
    private int tmpId;

    public TelescopeHorizontMask() {
	tmpId = (int) (1000 * Math.random());
    }

    public int getTmpId() {
	return tmpId;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public double getAngle() {
	return angle;
    }

    public void setAngle(double angle) {
	this.angle = angle;
    }

    public double getHorizont() {
	return horizont;
    }

    public void setHorizont(double horizont) {
	this.horizont = horizont;
    }

    public Telescope getTelescope() {
	return telescope;
    }

    public void setTelescope(Telescope telescope) {
	this.telescope = telescope;
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
	System.out.println("Test of equal mask: ");
	if (!(object instanceof TelescopeHorizontMask)) {
	    return false;
	}
	TelescopeHorizontMask other = (TelescopeHorizontMask) object;

	System.out.println(" this" + this.id + " " + this.toString() + "      " + " this" + other.id + " " + other.toString());

//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
	if (this.id == null || other.id == null) {
	    return false;
	}

	return this.id == other.id;
    }

    @Override
    public String toString() {
	return "{" + horizont + "; " + angle + '}';
    }

    @Override
    public int compareTo(Object o) {
	TelescopeHorizontMask tmp = (TelescopeHorizontMask) o;
	if (this.id < tmp.id) {
	    /* текущее меньше полученного */
	    return -1;
	} else if (this.id > tmp.id) {
	    /* текущее больше полученного */
	    return 1;
	}
	/* текущее равно полученному */
	return 0;
    }

}
