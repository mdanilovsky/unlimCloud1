/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Zeonfile;
import entity.ZeonfilePK;
import java.util.List;
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
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author fyntom
 */
@Stateless
@Path("zeonfile")
public class ZeonfileFacadeREST extends AbstractFacade<Zeonfile> {
    @PersistenceContext(unitName = "zeonserverPU")
    private EntityManager em;

    private ZeonfilePK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;zfid=zfidValue;fid=fidValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        entity.ZeonfilePK key = new entity.ZeonfilePK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> zfid = map.get("zfid");
        if (zfid != null && !zfid.isEmpty()) {
            key.setZfid(new java.lang.Long(zfid.get(0)));
        }
        java.util.List<String> fid = map.get("fid");
        if (fid != null && !fid.isEmpty()) {
            key.setFid(new java.lang.Long(fid.get(0)));
        }
        return key;
    }

    public ZeonfileFacadeREST() {
        super(Zeonfile.class);
    }

    @POST
    @Override
    @Consumes({ "application/json"})
    public void create(Zeonfile entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({ "application/json"})
    public void edit(Zeonfile entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        entity.ZeonfilePK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

  /*  @GET
    @Path("{id}")
    @Produces({ "application/json"})
    public Zeonfile find(@PathParam("id") PathSegment id) {
        entity.ZeonfilePK key = getPrimaryKey(id);
        return super.find(key);
    }*/
    
    @GET
    @Path("all/{zuid}")
    @Produces({ "application/json"})
    public List<Zeonfile> getAllUserFile(@PathParam("id") PathSegment zuid) {
        List resultList = em.createNamedQuery("Zeonfile.findByZuid").setParameter("zuid", zuid).getResultList();
        if(resultList.isEmpty())
            return null;
        return resultList;
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
