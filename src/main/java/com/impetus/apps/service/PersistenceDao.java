package com.impetus.apps.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 * @author vivek.mishra
 *
 */
public class PersistenceDao<R extends Object> implements Dao<R>
{


    //Considering there will be only one persistence unit for complete web application, later if required we will move it to  
    @PersistenceContext(unitName="personnel_cass_pu", type=PersistenceContextType.EXTENDED)
    protected EntityManager em;

        
    /* (non-Javadoc)
     * @see com.healthtrip.services.dao.api.Dao#persist(com.healthtrip.services.dao.api.Referencable)
     */
    @Override
    public void persist(R ob)
    {
        em.setFlushMode(FlushModeType.COMMIT);
        em.persist(ob);
//        em.flush();
    }

    public List findByEmail(String email)
    {
        String sql = "Select p from PersonnelEntity p where p.emailId= :emailId";
        Query q = em.createQuery(sql);
        q.setParameter("emailId", email);
        return q.getResultList();
    }
    /* (non-Javadoc)
     * @see com.healthtrip.services.dao.api.Dao#remove(com.healthtrip.services.dao.api.Referencable)
     */
    @Override
    public void remove(R obj)
    {
        em.remove(obj);
        
    }

    /* (non-Javadoc)
     * @see com.healthtrip.services.dao.api.Dao#find(java.lang.Class, java.lang.Object)
     */
    @Override
    public R find(Class<R> clazz, Object primaryKey)
    {
        return em.find(clazz, primaryKey);
        
    }

    /* (non-Javadoc)
     * @see com.healthtrip.services.dao.api.Dao#update(com.healthtrip.services.dao.api.Referencable)
     */
    @Override
    public void update(R obj)
    {
        em.merge(obj);
    }

    /**
     * @return the em
     */
    public EntityManager getEm()
    {
        return em;
    }

    /**
     * @param em the em to set
     */
    public void setEm(EntityManager em)
    {
        this.em = em;
    }

    public void flush()
    {
        em.flush();
    }
    
    public void clear()
    {
        em.clear();
    }

}
