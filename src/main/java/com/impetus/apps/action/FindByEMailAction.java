package com.impetus.apps.action;

import java.util.List;

import com.impetus.apps.entities.PersonnelEntity;
import com.impetus.apps.service.PersistenceService;

/**
 * @author vivek.mishra
 *
 */
public class FindByEMailAction
{
    private String emailId;
    
    private PersistenceService service;
    
    private List<PersonnelEntity> results;
    
    public String find()
    {
        results = service.findByEmail(emailId);
        if(results != null && !results.isEmpty())
        {
            return "success";
        }
        
        return "failure";
    }




    /**
     * @return the service
     */
    public PersistenceService getService()
    {
        return service;
    }


    /**
     * @param service the service to set
     */
    public void setService(PersistenceService service)
    {
        this.service = service;
    }


    /**
     * @return the emailId
     */
    public String getEmailId()
    {
        return emailId;
    }


    /**
     * @param emailId the emailId to set
     */
    public void setEmailId(String emailId)
    {
        this.emailId = emailId;
    }




    /**
     * @return the results
     */
    public List getResults()
    {
        return results;
    }




    /**
     * @param results the results to set
     */
    public void setResults(List results)
    {
        this.results = results;
    }

    
}
