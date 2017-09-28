/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.kiam.entities.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author PSEVO tochka
 */
@Entity
@Table(name = "userTypes")
public class UserType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "This isb ab requered field")
    @Size(min = 5, max = 30, message = "The name length is between 5 and 30 symbols ")
    private String name;

    private int rights = 0;  /// int bit = (c >> i) & 1  -- get an i-th bit

    @Transient
    private String binRights = "000000000";
    @Transient
    private int binRightsLen = binRights.length();

//    @Transient
//    private boolean[] boolRights = new boolean[20];
    @OneToMany
    @JoinTable(name = "users")
    @JoinColumn(name = "type_id")
    private List<User> userList = new ArrayList<>();

    public UserType() {
	rights = 0;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getRights() {

	return rights;
    }

    /**
     * Inverse a bit at given position pos (from end) in Binary Representation o
     * rights
     * <br />
     *
     * @param pos
     */
    public void changeBit(int pos) {
	System.out.println("   " + binRights + "  " + binRightsLen + "   >> " + pos);
	pos = binRightsLen - pos;
	StringBuilder stb = new StringBuilder(binRights);
	if (binRights.charAt(pos) == '1') {
	    stb.setCharAt(pos, '0');
	} else {
	    stb.setCharAt(pos, '1');
	}
	binRights = stb.toString();

	System.out.println("    binRights " + stb.toString() + "    pos " + pos);
	System.out.println("    stb " + binRights + "    pos " + pos + "      charAt(pos) " + binRights.charAt(pos));
	rights = Integer.parseInt(binRights, 2);

    }

    /**
     * Generate '+' if n !=0 and '-' otherwise
     *
     * @param n
     * @return
     */
    public String createCharByInt(char n) {
	if (n == '0') {
	    return "-";
	} else {
	    return "+";
	}
    }

    /**
     * Set up a bitNames for display
     *
     * @param pos
     * @return
     */
    public String bitName(int pos) {
	pos = 1 + pos % 8;
	if (pos == 1) {
	    return "Read Files";
	} else if (pos == 2) {
	    return "Download Files";
	} else if (pos == 3) {
	    return "Upload Files";
	} else if (pos == 4) {
	    return "Edit Files";
	} else if (pos == 5) {
	    return "Remove Files";
	} else if (pos == 6) {
	    return "Read Tables";
	} else if (pos == 7) {
	    return "Edit Tables";
	} else if (pos == 8) {
	    return "Remove Records";
	}
	return "";
    }

    public void setRights(int rights) {
	this.rights = rights;
	//getBinRightsLen();
    }

    public String getBinRights() {
	getBinRightsLen();
	return binRights;
    }

    public void setBinRights(String binRights) {
	this.binRights = binRights;
    }

    /**
     * At first creates a Binary String with length 10 of a given rights
     * field/<br /> Then return its length
     *
     * @return
     */
    public int getBinRightsLen() {
	binRights = Integer.toBinaryString(rights);
	while (binRights.length() < 10) {
	    binRights = "0" + binRights;
	}
	binRightsLen = binRights.length();
	return binRightsLen;
    }

    public List<User> getUserList() {
	return userList;
    }

    public void setUserList(List<User> userList) {
	this.userList = userList;
    }

    @Override
    public int hashCode() {
	int hash = 100;
	hash += (id != null ? id.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof UserType)) {
	    return false;
	}
	UserType other = (UserType) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "UserType[ id=" + id + ", name= " + name + ", rights=" + rights + " ]";
    }

}
