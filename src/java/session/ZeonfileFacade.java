/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.File;
import entity.Zeonfile;
import entity.Zeonuser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fyntom
 */
@Stateless
public class ZeonfileFacade extends AbstractFacade<Zeonfile> {
    @PersistenceContext(unitName = "zeonserverPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ZeonfileFacade() {
        super(Zeonfile.class);
    }
    
    public void addZeonfile(File EF,String name,String path,String lmodified,Zeonuser ZUID){
        Zeonfile ZF=new Zeonfile();
        
        ZF.setFid(EF);
        ZF.setZuid(ZUID);
        ZF.setName(name);
        ZF.setPath(path);
        ZF.setLastmodified(lmodified);
                
        em.persist(ZF);
    }
    
}
