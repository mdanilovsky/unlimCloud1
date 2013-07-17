/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.File;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fyntom
 */
@Stateless
public class FileFacade extends AbstractFacade<File> {
    @PersistenceContext(unitName = "zeonserverPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FileFacade() {
        super(File.class);
    }
    
    public File addFile(String hash){
        File EF=new File();
        EF.setHash(hash);
         
        em.persist(EF);
        em.flush();
        
        return EF;
    }
    
}
