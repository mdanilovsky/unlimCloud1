/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.File;
import entity.Zeonfile;
import entity.Zeonuser;
import java.util.List;
import javax.ejb.EJB;
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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import session.FileFacade;
import session.ZeonfileFacade;
import session.ZeonuserFacade;

/**
 *
 * @author fyntom
 */
@Stateless
@Path("zeonfile")
public class ZeonfileFacadeREST extends AbstractFacade<Zeonfile> {

    @PersistenceContext(unitName = "zeonserverPU")
    private EntityManager em;
    @EJB
    ZeonuserFacade zeonUserFacade;
    @EJB
    FileFacade fileFacade;
    @EJB
    ZeonfileFacade zeonfileFacade;
    @EJB
    ZeonuserFacade zeonuserFacade;

    public ZeonfileFacadeREST() {
        super(Zeonfile.class);
    }

    @POST
    @Consumes({"application/json"})
    public void create(final zfPckage in) {
        
       Zeonuser ZU=zeonuserFacade.find(Long.valueOf(in.tocken));
       
       if(ZU==null)
           return;
        
        List<File> resultList = em.createNamedQuery("File.findByHash").setParameter("hash", in.hash).getResultList();
        if (resultList.isEmpty()) {
            // Если такого файла нет, то надо его зарезервировать
            File EF=fileFacade.addFile(in.hash);    
            // Сохраняем наш файл
            zeonfileFacade.addZeonfile(EF, in.name, in.path, in.lmodified,ZU);  
            return;
        }
        // Сохраняем наш файл
        //System.out.println("[create] "+resultList.get(0).getFid());
        List<Zeonfile> zfList = em.createNamedQuery("Zeonfile.findByPathNameZuid").setParameter("path", in.path).setParameter("name", in.name).setParameter("zuid", ZU).getResultList();
        
        if(zfList.isEmpty())
            zeonfileFacade.addZeonfile(resultList.get(0), in.name, in.path, in.lmodified,ZU); 
        else {
            zfList.get(0).setName(in.name);
            zfList.get(0).setLastmodified(in.lmodified);
            em.merge(zfList.get(0));
        }
    }

    @PUT
    @Override
    @Consumes({"application/json"})
    public void edit(Zeonfile entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{zfid}")
    public void remove(@PathParam("zfid") Long zfid) {
        super.remove(super.find(zfid));
    }

    @GET
    @Path("{name}")
    @Produces({"application/json"})
    public String getFidByName(@PathParam("name") String name) {
        List<Zeonfile> resultList = em.createNamedQuery("Zeonfile.findByName").setParameter("name", name).getResultList();
        if (resultList.isEmpty()) {
            // System.out.println("Пусто однако "+zuid);
            return "0";
        }
        return String.valueOf(resultList.get(0).getFid().getFid());
    }

    @GET
    @Path("all/{zuid}")
    @Produces({"application/xml"})
    public List<Zeonfile> getAllUserFile(@PathParam("zuid") Long zuid) {
        Zeonuser ZU = zeonUserFacade.find(zuid);
        if (ZU == null) {
            return null;
        }
        //System.out.println(ZU.toString());
        List <Zeonfile>resultList = em.createNamedQuery("Zeonfile.findByZuid").setParameter("zuid", ZU).getResultList();
                
        return resultList;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @XmlRootElement
    public static class zfPckage {
    @XmlElement public String path;
    @XmlElement public String name;
    @XmlElement public String hash;
    @XmlElement public String lmodified;
    @XmlElement public String tocken;
}
}
