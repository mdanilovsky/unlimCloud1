/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    @NamedQuery(name = "Zeonfile.findAll", query = "SELECT z FROM Zeonfile z"),
    @NamedQuery(name = "Zeonfile.findByZfid", query = "SELECT z FROM Zeonfile z WHERE z.zeonfilePK.zfid = :zfid"),
    @NamedQuery(name = "Zeonfile.findByLastmodified", query = "SELECT z FROM Zeonfile z WHERE z.lastmodified = :lastmodified"),
    @NamedQuery(name = "Zeonfile.findByFid", query = "SELECT z FROM Zeonfile z WHERE z.zeonfilePK.fid = :fid"),
    @NamedQuery(name = "Zeonfile.findByPath", query = "SELECT z FROM Zeonfile z WHERE z.path = :path"),
    @NamedQuery(name = "Zeonfile.findByZuid", query = "SELECT z FROM Zeonfile z WHERE z.zuid = :zuid"),
    @NamedQuery(name = "Zeonfile.findByName", query = "SELECT z FROM Zeonfile z WHERE z.name = :name")})
public class Zeonfile implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ZeonfilePK zeonfilePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String lastmodified;
    @Size(max = 450)
    private String path;
    @Size(max = 450)
    private String name;
    @JoinColumn(name = "zuid", referencedColumnName = "zuid")
    @ManyToOne(optional = false)
    private Zeonuser zuid;
    @JoinColumn(name = "fid", referencedColumnName = "fid", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private File file;

    public Zeonfile() {
    }

    public Zeonfile(ZeonfilePK zeonfilePK) {
        this.zeonfilePK = zeonfilePK;
    }

    public Zeonfile(ZeonfilePK zeonfilePK, String lastmodified) {
        this.zeonfilePK = zeonfilePK;
        this.lastmodified = lastmodified;
    }

    public Zeonfile(long zfid, long fid) {
        this.zeonfilePK = new ZeonfilePK(zfid, fid);
    }

    public ZeonfilePK getZeonfilePK() {
        return zeonfilePK;
    }

    public void setZeonfilePK(ZeonfilePK zeonfilePK) {
        this.zeonfilePK = zeonfilePK;
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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zeonfilePK != null ? zeonfilePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zeonfile)) {
            return false;
        }
        Zeonfile other = (Zeonfile) object;
        if ((this.zeonfilePK == null && other.zeonfilePK != null) || (this.zeonfilePK != null && !this.zeonfilePK.equals(other.zeonfilePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Zeonfile[ zeonfilePK=" + zeonfilePK + " ]";
    }
    
}
