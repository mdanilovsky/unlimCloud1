/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fyntom
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hashfile.findAll", query = "SELECT h FROM Hashfile h"),
    @NamedQuery(name = "Hashfile.findByHid", query = "SELECT h FROM Hashfile h WHERE h.hid = :hid"),
    @NamedQuery(name = "Hashfile.findByHash", query = "SELECT h FROM Hashfile h WHERE h.hash = :hash"),
    @NamedQuery(name = "Hashfile.findByPosition", query = "SELECT h FROM Hashfile h WHERE h.position = :position")})
public class Hashfile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long hid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    private String hash;
    private Integer position;
    @JoinColumn(name = "fid", referencedColumnName = "fid")
    @ManyToOne(optional = false)
    private File fid;
    @JoinColumn(name = "cuid", referencedColumnName = "cuid")
    @ManyToOne(optional = false)
    private Clouduser cuid;

    public Hashfile() {
    }

    public Hashfile(Long hid) {
        this.hid = hid;
    }

    public Hashfile(Long hid, String hash) {
        this.hid = hid;
        this.hash = hash;
    }

    public Long getHid() {
        return hid;
    }

    public void setHid(Long hid) {
        this.hid = hid;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public File getFid() {
        return fid;
    }

    public void setFid(File fid) {
        this.fid = fid;
    }

    public Clouduser getCuid() {
        return cuid;
    }

    public void setCuid(Clouduser cuid) {
        this.cuid = cuid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hid != null ? hid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hashfile)) {
            return false;
        }
        Hashfile other = (Hashfile) object;
        if ((this.hid == null && other.hid != null) || (this.hid != null && !this.hid.equals(other.hid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Hashfile[ hid=" + hid + " ]";
    }
    
}
