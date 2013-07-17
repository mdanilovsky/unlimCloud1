/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Zeonuser;
import java.util.List;
import java.util.UUID;
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
    
    public Zeonuser findByZuid(Long zuid){
       System.out.println(zuid);
       
        List<Zeonuser> resultList = em.createNamedQuery("Zeonuser.findByZfid").setParameter("zuid", zuid).getResultList();
        System.out.println(resultList.size());
        if(resultList.isEmpty())
            return null;
        return resultList.get(0);
    }
    
        @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int addUser(final String login, final String password, final String passwordTwo) {
        try {
            if (login != null && password != null && passwordTwo != null && password.equals(passwordTwo)) {
                List resultList = em.createNamedQuery("Zeonuser.findByEmail").setParameter("email", login).getResultList();
                if (resultList.size() == 0) {
                    Zeonuser user = new Zeonuser(login, password,UUID.randomUUID().toString().replace("-", "").substring(0, 10));
                    em.persist(user);                    
                    return 0;
                } else {
                    return 3;
}
            } else {
                return 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
}
