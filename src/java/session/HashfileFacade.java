/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Hashfile;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fyntom
 */
@Stateless
public class HashfileFacade extends AbstractFacade<Hashfile> {
    @PersistenceContext(unitName = "zeonserverPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HashfileFacade() {
        super(Hashfile.class);
    }
    
}
