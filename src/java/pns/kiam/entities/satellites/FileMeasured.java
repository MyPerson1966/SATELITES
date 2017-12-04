/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.kiam.entities.satellites;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import pns.kiam.Utils.StringUtils;

/**
 *
 * @author User
 */
@Entity
@Table(name = "File_measured")
public class FileMeasured implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;

    private int year = 0;
    private int month = 0;
    private int date = 0;

    @Lob
    @Column(columnDefinition = "longtext")
    private String content = "";
    private String strHash = "";
    private String fileAttr = "";

    @Column(unique = true)
    private long intHash = 0;
    private String fileName = "";
    private long uploadedMoment = 0;  // moment of uploading file in local time

    public FileMeasured() {
    }

    public FileMeasured(int y, int m, int d, String c, String f, long mm) {
        year = y;
        month = m;
        date = d;

        try {
            strHash = pns.utils.strings.RStrings.strToHash(c, "MD5");
            strHash = pns.utils.numbers.RConverter.toHex(strHash.getBytes());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FileMeasured.class.getName()).log(Level.SEVERE, null, ex);
        }
        intHash = pns.utils.numbers.RConverter.toLong(strHash.getBytes());
        content = c;
        fileName = f;
        uploadedMoment = mm;
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

    public String getStrHash() {
        return strHash;
    }

    public long getIntHash() {
        return intHash;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getUploadedMoment() {
        //return uploadMomentUTC();
        return uploadedMoment;
    }

    public void setUploadedMoment(long uploadedMoment) {
        this.uploadedMoment = uploadedMoment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        hash += (int) content.length() * 256;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FileMeasured)) {
            return false;
        }
        FileMeasured other = (FileMeasured) object;
        boolean res = true;
//        if (this.fileName != null && other.fileName != null) {
//            res= this.fileName.trim().equals(other.fileName.trim());
//        }
        if (this.content != null && other.content != null) {
            System.out.println("Content { " + fileName + ";" + other.fileName + "}");
            res = res && this.content.trim().equals(other.content.trim());
        }

        return res;
    }

    @Override
    public String toString() {
        return "FileMeasured[ id=" + id + " monthe=" + month + " date=" + date + " ]";
    }

}
