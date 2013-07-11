/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Clouduser;

import java.util.List;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fyntom
 */
@Stateless
public class ClouduserFacade extends AbstractFacade<Clouduser> {
    @PersistenceContext(unitName = "zeonserverPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClouduserFacade() {
        super(Clouduser.class);
    }
    
    public Long sumFreespace(){
        List resultList = em.createNamedQuery("Clouduser.sumFreespace").getResultList();

        return Long.valueOf(resultList.get(0).toString());
}
    
   
}
