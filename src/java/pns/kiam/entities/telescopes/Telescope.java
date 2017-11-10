/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.kiam.entities.telescopes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 *
 * @author User
 */
@Entity
@Table(name = "telescopes")
public class Telescope implements Serializable, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(-100000)
    @Max(100000)
    private double x;
    @Min(-100000)
    @Max(100000)
    private double y;

    @Min(-100000)
    @Max(100000)
    private double z;

    @Min(-500)
    @Max(15000)
    private double height = 0;

    @Min(-90)
    @Max(90)
    private double latitude = 0;

    @Min(-180)
    @Max(180)
    private double longitude = 0;

    @Column(unique = true)
    @Min(1)
    @Max(1000000)
    private long identifier;
    private String location = "", name = "";

    @Size(max = 1024)
    private String description = "";

    @Size(max = 1024)
    private String comment = "";

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.REMOVE)
    @JoinTable(name = "TelescopeMasks")
    @JoinColumn(name = "telescope_id")
    @OrderBy("horizont ASC ")
    private List<TelescopeHorizontMask> telescopeMask = new ArrayList<>();

    @Transient
    private boolean selected = false;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public boolean isSelected() {
	return selected;
    }

    public void setSelected(boolean selected) {
	this.selected = selected;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (id != null ? id.hashCode() : 0);
	return hash;
    }

    public double getX() {
	return x;
    }

    public void setX(double x) {
	this.x = x;
    }

    public double getY() {
	return y;
    }

    public void setY(double y) {
	this.y = y;
    }

    public double getZ() {
	return z;
    }

    public void setZ(double z) {
	this.z = z;
    }

    public double getHeight() {
	return height;
    }

    public void setHeight(double height) {
	this.height = height;
    }

    public double getLatitude() {
	return latitude;
    }

    public void setLatitude(double latitude) {
	this.latitude = latitude;
    }

    public long getIdentifier() {
	return identifier;
    }

    public void setIdentifier(long identifier) {
	this.identifier = identifier;
    }

    public String getLocation() {
	return location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getComment() {
	return comment;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }

    public double getLongitude() {
	return longitude;
    }

    public void setLongitude(double longitude) {
	this.longitude = longitude;
    }

    public List<TelescopeHorizontMask> getTelescopeMask() {
	return telescopeMask;
    }

    public void setTelescopeMask(List<TelescopeHorizontMask> telescopeMask) {
	this.telescopeMask = telescopeMask;
    }

    public String getShortName() {
	String res = name;
	if (res.length() > 15) {
	    res = name.substring(0, 10).trim();
	    if (name.trim().length() == res.length()) {
		return res;
	    }
	    return res + "  . . . ";
	}
	return res;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Telescope)) {
	    return false;
	}
	Telescope other = (Telescope) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return " Telesope{"
		+ "  id=" + id
		+ ", identifier=" + identifier
		+ ", location=" + location + ", name=" + name
		+ ", x=" + x
		+ ", y=" + y
		+ ", z=" + z
		+ ", height=" + height
		+ ", latitude=" + latitude
		+ ", longitude=" + longitude
		+ ", description=" + description
		+ ", comment=" + comment
		+ ", telescopeMaskSize=" + telescopeMask.size() + "}";
    }

    @Override
    public int compareTo(Object o) {

	if (!(o instanceof Telescope)) {
	    return -1;
	}
	Telescope tmp = (Telescope) o;
	if (this.identifier < tmp.identifier) {
	    /* текущее меньше полученного */
	    return -1;
	} else if (this.identifier > tmp.identifier) {
	    /* текущее больше полученного */
	    return 1;
	}
	/* текущее равно полученному */
	return 0;

    }

}
