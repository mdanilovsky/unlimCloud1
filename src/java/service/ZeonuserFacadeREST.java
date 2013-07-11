/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Zeonuser;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author fyntom
 */
@Stateless
@Path("zeonuser")
public class ZeonuserFacadeREST extends AbstractFacade<Zeonuser> {

    @PersistenceContext(unitName = "zeonserverPU")
    private EntityManager em;

    public ZeonuserFacadeREST() {
        super(Zeonuser.class);
    }

    @POST
    @Override
    @Consumes({"application/json"})
    public void create(Zeonuser entity) {
        System.out.println(entity.getEmail()+"#"+entity.getPassword());
        super.create(entity);
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Zeonuser find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Path("{email}/{password}")
    @Produces({"application/json"})
    public String getTocken(@PathParam("email") String email, @PathParam("password") String password) {

        Pattern pat = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pat.matcher(email);
        if (email == null || !matcher.matches()) {
            return "email-not-valid";
        }
        
        List<Zeonuser> resultList = em.createNamedQuery("Zeonuser.findByEmail").setParameter("email", email).getResultList();
        
        if (!resultList.isEmpty()) {           
            Zeonuser validUser = resultList.get(0);
            if (!password.equals(validUser.getPassword())) {
                return "not-valid-password";
            }

            return String.valueOf(validUser.getZuid());
        }
        return "no-such-user";
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
