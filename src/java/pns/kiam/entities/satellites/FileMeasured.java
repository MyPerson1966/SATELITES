/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.kiam.entities.satellites;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author User
 */
@Entity
@Table(name = "File_measured")
public class FileMeasured implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int year = 0;
    private int month = 0;
    private int date = 0;

    @Lob
    @Column(columnDefinition = "text")
    @Size(max = 20480)
    private String content = "";

    public FileMeasured() {
    }

    public FileMeasured(int y, int m, int d, String c) {
	year = y;
	month = m;
	date = d;
	content = c;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public int getYear() {
	return year;
    }

    public void setYear(int year) {
	this.year = year;
    }

    public int getMonth() {
	return month;
    }

    public void setMonth(int month) {
	this.month = month;
    }

    public int getDate() {
	return date;
    }

    public void setDate(int date) {
	this.date = date;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
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
	if (!(object instanceof FileMeasured)) {
	    return false;
	}
	FileMeasured other = (FileMeasured) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	if ((this.content == null && other.content != null) || (this.content != null && !this.content.trim().equals(other.content.trim()))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "FileMeasured[ id=" + id + " ]";
    }

}
