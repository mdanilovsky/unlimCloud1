/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author fyntom
 */
@Embeddable
public class ZeonfilePK implements Serializable {
    @Basic(optional = false)
    private long zfid;
    @Basic(optional = false)
    @NotNull
    private long fid;

    public ZeonfilePK() {
    }

    public ZeonfilePK(long zfid, long fid) {
        this.zfid = zfid;
        this.fid = fid;
    }

    public long getZfid() {
        return zfid;
    }

    public void setZfid(long zfid) {
        this.zfid = zfid;
    }

    public long getFid() {
        return fid;
    }

    public void setFid(long fid) {
        this.fid = fid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) zfid;
        hash += (int) fid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ZeonfilePK)) {
            return false;
        }
        ZeonfilePK other = (ZeonfilePK) object;
        if (this.zfid != other.zfid) {
            return false;
        }
        if (this.fid != other.fid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ZeonfilePK[ zfid=" + zfid + ", fid=" + fid + " ]";
    }
    
}
