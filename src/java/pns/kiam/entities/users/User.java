package pns.kiam.entities.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import pns.kiam.entities.telescopes.Telescope;

/**
 * @author PSEVO tochka
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email = "";

    private String password = "";

    @Size(max = 1054)
    private String comment = "";

    private long moment = 0;

    private boolean isActive = true;

    @OneToMany
    @JoinColumn(name = "telescope_id")
    private List<Telescope> userTelescopeList = new ArrayList<>();

    @ManyToOne
    @NotNull
    private UserType userType;

    public User() {
	moment = System.currentTimeMillis();
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getComment() {
	return comment;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }

    public long getMoment() {
	return moment;
    }

    public boolean isIsActive() {
	return isActive;
    }

    public void setIsActive(boolean isActive) {
	this.isActive = isActive;
    }

    public List<Telescope> getUserTelescopeList() {
	return userTelescopeList;
    }

    public void setUserTelescopeList(List<Telescope> userTelescopeList) {
	this.userTelescopeList = userTelescopeList;
    }

    public UserType getUserType() {
	return userType;
    }

    public void setUserType(UserType userType) {
	this.userType = userType;
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
	if (!(object instanceof User)) {
	    return false;
	}
	User other = (User) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "pns.kiam.entities.users.Users[ id=" + id + ""
		+ " email =" + email + ""
		+ " password=" + password + ""
		+ " moment=" + moment
		+ " ]";
    }

}
