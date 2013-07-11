/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fyntom
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clouduser.findAll", query = "SELECT c FROM Clouduser c"),
    @NamedQuery(name = "Clouduser.sumFreespace", query = "SELECT SUM(c.freespace) FROM Clouduser c"),
    @NamedQuery(name = "Clouduser.getPair", query = "SELECT c FROM Clouduser c WHERE c.freespace>10000"),
    @NamedQuery(name = "Clouduser.findByCuid", query = "SELECT c FROM Clouduser c WHERE c.cuid = :cuid"),
    @NamedQuery(name = "Clouduser.findByLogin", query = "SELECT c FROM Clouduser c WHERE c.login = :login"),
    @NamedQuery(name = "Clouduser.findByPassword", query = "SELECT c FROM Clouduser c WHERE c.password = :password"),
    @NamedQuery(name = "Clouduser.findByFreespace", query = "SELECT c FROM Clouduser c WHERE c.freespace = :freespace")})
   
public class Clouduser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long cuid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String password;
    private BigInteger freespace;
    @JoinColumn(name = "cid", referencedColumnName = "cid")
    @ManyToOne(optional = false)
    private Cloudtype cid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuid")
    private Collection<Hashfile> hashfileCollection;

    public Clouduser() {
    }

    public Clouduser(Long cuid) {
        this.cuid = cuid;
    }

    public Clouduser(Long cuid, String login, String password) {
        this.cuid = cuid;
        this.login = login;
        this.password = password;
    }

    public Long getCuid() {
        return cuid;
    }

    public void setCuid(Long cuid) {
        this.cuid = cuid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigInteger getFreespace() {
        return freespace;
    }

    public void setFreespace(BigInteger freespace) {
        this.freespace = freespace;
    }

    public Cloudtype getCid() {
        return cid;
    }

    public void setCid(Cloudtype cid) {
        this.cid = cid;
    }

    @XmlTransient
    public Collection<Hashfile> getHashfileCollection() {
        return hashfileCollection;
    }

    public void setHashfileCollection(Collection<Hashfile> hashfileCollection) {
        this.hashfileCollection = hashfileCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuid != null ? cuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clouduser)) {
            return false;
        }
        Clouduser other = (Clouduser) object;
        if ((this.cuid == null && other.cuid != null) || (this.cuid != null && !this.cuid.equals(other.cuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Clouduser[ cuid=" + cuid + " ]";
    }
    
}
