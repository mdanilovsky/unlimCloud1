/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Zeonuser;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fyntom
 */
@Stateless
public class ZeonuserFacade extends AbstractFacade<Zeonuser> {
    @PersistenceContext(unitName = "zeonserverPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ZeonuserFacade() {
        super(Zeonuser.class);
    }
    
        @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Long addUser(final String login, final String password, final String passwordTwo) {
        try {
            if (login != null && password != null && passwordTwo != null && password.equals(passwordTwo)) {
                List resultList = em.createNamedQuery("Zeonuser.findByEmail").setParameter("email", login).getResultList();
                if (resultList.size() == 0) {
                    Zeonuser user = new Zeonuser(null,login, password,null);
                    em.persist(user);                    
                    return user.getZuid();
                } else {
                    return Long.valueOf(3);
                }
            } else {
                return Long.valueOf(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Long.valueOf(1);
        }
    }
}
