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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ZEONFILE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zeonfile.findAll", query = "SELECT z FROM Zeonfile z"),
    @NamedQuery(name = "Zeonfile.findByZfid", query = "SELECT z FROM Zeonfile z WHERE z.zfid = :zfid"),
    @NamedQuery(name = "Zeonfile.findByLastmodified", query = "SELECT z FROM Zeonfile z WHERE z.lastmodified = :lastmodified"),
    @NamedQuery(name = "Zeonfile.findByPath", query = "SELECT z FROM Zeonfile z WHERE z.path = :path"),
    @NamedQuery(name = "Zeonfile.findByPathNameZuid", query = "SELECT z FROM Zeonfile z WHERE z.path = :path and z.name=:name and z.zuid=:zuid"),
    @NamedQuery(name = "Zeonfile.findByZuid", query = "SELECT z FROM Zeonfile z WHERE z.zuid = :zuid"),
    @NamedQuery(name = "Zeonfile.findByName", query = "SELECT z FROM Zeonfile z WHERE z.name = :name")})
public class Zeonfile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "zfid")
    private Long zfid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "lastmodified")
    private String lastmodified;
    @Size(max = 450)
    @Column(name = "path")
    private String path;
    @Size(max = 450)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "zuid", referencedColumnName = "zuid")
    @ManyToOne(optional = false)
    private Zeonuser zuid;
    @JoinColumn(name = "fid", referencedColumnName = "fid")
    @ManyToOne(optional = false)
    private File fid;

    public Zeonfile() {
    }

    public Zeonfile(Long zfid) {
        this.zfid = zfid;
    }

    public Zeonfile(Long zfid, String lastmodified) {
        this.zfid = zfid;
        this.lastmodified = lastmodified;
    }

    public Long getZfid() {
        return zfid;
    }

    public void setZfid(Long zfid) {
        this.zfid = zfid;
    }

    public String getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(String lastmodified) {
        this.lastmodified = lastmodified;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Zeonuser getZuid() {
        return zuid;
    }

    public void setZuid(Zeonuser zuid) {
        this.zuid = zuid;
    }

    public File getFid() {
        return fid;
    }

    public void setFid(File fid) {
        this.fid = fid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zfid != null ? zfid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zeonfile)) {
            return false;
        }
        Zeonfile other = (Zeonfile) object;
        if ((this.zfid == null && other.zfid != null) || (this.zfid != null && !this.zfid.equals(other.zfid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Zeonfile[ zfid=" + zfid + " ]";
    }
    
}
