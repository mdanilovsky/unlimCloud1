/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import conf.varAP;
import entity.Clouduser;
import entity.File;
import entity.Hashfile;
import java.math.BigInteger;
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
import session.ClouduserFacade;
import session.FileFacade;


/**
 *
 * @author fyntom
 */
@Stateless
@Path("hashfile")
public class HashfileFacadeREST extends AbstractFacade<Hashfile> {
    @PersistenceContext(unitName = "zeonserverPU")
    private EntityManager em;
    
    @EJB
    FileFacade file;
    
    @EJB
    ClouduserFacade clouduser;

    public HashfileFacadeREST() {
        super(Hashfile.class);
    }

    @POST
    @Consumes({"application/json"})
    public void create(final hashPckage in) {
        File ZF=file.find(in.fid);
        
        List <Clouduser> resultList = em.createNamedQuery("Clouduser.findByLogin").setParameter("login", in.cu).getResultList();
        if(resultList.isEmpty()){
            return;
        }       
        
        Hashfile HF=new Hashfile();
        HF.setFid(ZF);
        HF.setPosition(in.position);
        HF.setHash(in.hash);
        HF.setCuid(resultList.get(0));
        
        em.persist(HF);
        
        //-- Уменьшили место у пользователя
        /*TODO вытаскивать инфу с клиента*/
        Long resFS=resultList.get(0).getFreespace().longValue()-Long.valueOf(varAP.partsize);
        resultList.get(0).setFreespace(BigInteger.valueOf(resFS));        
    }
    
    @GET
    @Path("{hash}")
    @Produces({"application/xml"})
    public List<Hashfile> find(@PathParam("hash") String hash) {
        List resultList = em.createNamedQuery("Hashfile.findByHash").setParameter("hash", hash).getResultList();
        if(resultList.isEmpty()){
            System.out.println("Пусто однако "+hash);
            return null;
       }
        return resultList;
    }

    @GET
    @Path("fid/{fid}")
    @Produces({"application/xml"})
    public List<Hashfile> findByfid(@PathParam("fid") Long fid) {
        File ZF=file.find(fid);
        
        if(ZF==null)
            return null;
        
        List resultList = em.createNamedQuery("Hashfile.findByFid").setParameter("fid", ZF).getResultList();
        if(resultList.isEmpty()){
            System.out.println("Пусто однако "+fid);
            return null;
       }
        return resultList;
    }


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    @XmlRootElement
    public static class hashPckage {
    @XmlElement public String hash;
    @XmlElement public Integer position;
    @XmlElement public Long fid;
    @XmlElement public String cu;
    @XmlElement public Long freespace;
}
}
