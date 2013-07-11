/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import conf.varAP;
import entity.Clouduser;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author fyntom
 */
@Stateless
@Path("clouduser")
public class ClouduserFacadeREST extends AbstractFacade<Clouduser> {
    @PersistenceContext(unitName = "zeonserverPU")
    private EntityManager em;

    public ClouduserFacadeREST() {
        super(Clouduser.class);
    }
/*
    @POST
    @Override
    @Consumes({"application/json"})
    public void create(Clouduser entity) {
        super.create(entity);
    }
*/
    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Clouduser find(@PathParam("id") Long id) {
        return super.find(id);
    }
    
    @PUT
    @Path("{id}")
    @Produces({"application/json"})
    public void setFreeSpace(@PathParam("id") Long id) {
        Clouduser CU=super.find(id);
        
        Long resFS=CU.getFreespace().longValue()-Long.valueOf(varAP.partsize);
        
        CU.setFreespace(BigInteger.valueOf(resFS));
        em.persist(CU);        
    }

    @GET
    @Produces({"application/xml"})
    public Clouduser getCloudUser() {
        List <Clouduser> resultList = em.createNamedQuery("Clouduser.getPair").getResultList();

        Random random = new Random();
        int res = random.nextInt(resultList.size());
               
        return resultList.get(res);
    }
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
