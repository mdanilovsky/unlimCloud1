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
    @NamedQuery(name = "File.findAll", query = "SELECT f FROM File f"),
    @NamedQuery(name = "File.findByFid", query = "SELECT f FROM File f WHERE f.fid = :fid"),
    @NamedQuery(name = "File.findByHash", query = "SELECT f FROM File f WHERE f.hash = :hash")})
public class File implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long fid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    private String hash;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "file")
    private Collection<Zeonfile> zeonfileCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fid")
    private Collection<Hashfile> hashfileCollection;

    public File() {
    }

    public File(Long fid) {
        this.fid = fid;
    }

    public File(Long fid, String hash) {
        this.fid = fid;
        this.hash = hash;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @XmlTransient
    public Collection<Zeonfile> getZeonfileCollection() {
        return zeonfileCollection;
    }

    public void setZeonfileCollection(Collection<Zeonfile> zeonfileCollection) {
        this.zeonfileCollection = zeonfileCollection;
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
        hash += (fid != null ? fid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof File)) {
            return false;
        }
        File other = (File) object;
        if ((this.fid == null && other.fid != null) || (this.fid != null && !this.fid.equals(other.fid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.File[ fid=" + fid + " ]";
    }
    
}
