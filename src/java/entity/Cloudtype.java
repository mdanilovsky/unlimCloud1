/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "Cloudtype.findAll", query = "SELECT c FROM Cloudtype c"),
    @NamedQuery(name = "Cloudtype.findByCid", query = "SELECT c FROM Cloudtype c WHERE c.cid = :cid"),
    @NamedQuery(name = "Cloudtype.findByName", query = "SELECT c FROM Cloudtype c WHERE c.name = :name"),
    @NamedQuery(name = "Cloudtype.findByApikey", query = "SELECT c FROM Cloudtype c WHERE c.apikey = :apikey"),
    @NamedQuery(name = "Cloudtype.findByApipassword", query = "SELECT c FROM Cloudtype c WHERE c.apipassword = :apipassword")})
public class Cloudtype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long cid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String apikey;
    @Size(max = 100)
    private String apipassword;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cid")
    private Collection<Clouduser> clouduserCollection;

    public Cloudtype() {
    }

    public Cloudtype(Long cid) {
        this.cid = cid;
    }

    public Cloudtype(Long cid, String name, String apikey) {
        this.cid = cid;
        this.name = name;
        this.apikey = apikey;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getApipassword() {
        return apipassword;
    }

    public void setApipassword(String apipassword) {
        this.apipassword = apipassword;
    }

    @XmlTransient
    public Collection<Clouduser> getClouduserCollection() {
        return clouduserCollection;
    }

    public void setClouduserCollection(Collection<Clouduser> clouduserCollection) {
        this.clouduserCollection = clouduserCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cid != null ? cid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cloudtype)) {
            return false;
        }
        Cloudtype other = (Cloudtype) object;
        if ((this.cid == null && other.cid != null) || (this.cid != null && !this.cid.equals(other.cid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Cloudtype[ cid=" + cid + " ]";
    }
    
}
