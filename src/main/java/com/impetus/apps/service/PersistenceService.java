package com.impetus.apps.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.impetus.apps.entities.PersonnelEntity;

/**
 * @author vivek.mishra
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
public class PersistenceService
{

    private Dao<PersonnelEntity> dao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void persist(PersonnelEntity object)
    {
        dao.persist(object);
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public List<PersonnelEntity> findByEmail(String emailId)
    {
        List<PersonnelEntity> results = (List<PersonnelEntity>) ((PersistenceDao)dao).findByEmail(emailId);
       return results; 
    }
    /**
     * @return the dao
     */
    public Dao<PersonnelEntity> getDao()
    {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(Dao<PersonnelEntity> dao)
    {
        this.dao = dao;
    }
    
    
}
