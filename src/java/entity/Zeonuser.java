/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fyntom
 */
@Entity
@Table(name = "ZEONUSER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zeonuser.findAll", query = "SELECT z FROM Zeonuser z"),
    @NamedQuery(name = "Zeonuser.findByZuid", query = "SELECT z FROM Zeonuser z WHERE z.zuid = :zuid"),
    @NamedQuery(name = "Zeonuser.findByEmail", query = "SELECT z FROM Zeonuser z WHERE z.email = :email"),
    @NamedQuery(name = "Zeonuser.findByPassword", query = "SELECT z FROM Zeonuser z WHERE z.password = :password"),
    @NamedQuery(name = "Zeonuser.findByTocken", query = "SELECT z FROM Zeonuser z WHERE z.tocken = :tocken")})
public class Zeonuser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "zuid")
    private Long zuid;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Недопустимый адрес электронной почты")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "tocken")
    private String tocken;

    public Zeonuser() {
    }

    public Zeonuser(Long zuid) {
        this.zuid = zuid;
    }

    public Zeonuser(Long zuid, String email, String password, String tocken) {
        this.zuid = zuid;
        this.email = email;
        this.password = password;
        this.tocken = tocken;
    }
    
    public Zeonuser(String email, String password, String tocken) {
        this.email = email;
        this.password = password;
        this.tocken = tocken;
    }

    public Long getZuid() {
        return zuid;
    }

    public void setZuid(Long zuid) {
        this.zuid = zuid;
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

    public String getTocken() {
        return tocken;
    }

    public void setTocken(String tocken) {
        this.tocken = tocken;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zuid != null ? zuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zeonuser)) {
            return false;
        }
        Zeonuser other = (Zeonuser) object;
        if ((this.zuid == null && other.zuid != null) || (this.zuid != null && !this.zuid.equals(other.zuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Zeonuser[ zuid=" + zuid + " ]";
    }
    
}
