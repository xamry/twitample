package com.impetus.apps.action;

import java.util.UUID;

import com.impetus.apps.entities.AddressEntity;
import com.impetus.apps.entities.PersonnelEntity;
import com.impetus.apps.service.PersistenceService;

/**
 * @author vivek.mishra
 *
 */
public class PersistenceAction
{
    private String personName;
    private String street;
    private String emailId;
    private int age;
    
    private PersistenceService service;
    
    public String execute()
    {
        PersonnelEntity object = new PersonnelEntity();
        object.setPersonId(UUID.randomUUID().toString());
        object.setPersonName(getPersonName());
        object.setEmailId(emailId);
        object.setAge(age);
        AddressEntity address = new AddressEntity();
        address.setAddressId(UUID.randomUUID().toString());
        address.setStreet(getStreet());
        object.setAddress(address);
        service.persist(object);
        return "success";
    }


    /**
     * @return the personName
     */
    public String getPersonName()
    {
        return personName;
    }


    /**
     * @param personName the personName to set
     */
    public void setPersonName(String personName)
    {
        this.personName = personName;
    }


    /**
     * @return the street
     */
    public String getStreet()
    {
        return street;
    }


    /**
     * @param street the street to set
     */
    public void setStreet(String street)
    {
        this.street = street;
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
     * @return the age
     */
    public int getAge()
    {
        return age;
    }


    /**
     * @param age the age to set
     */
    public void setAge(int age)
    {
        this.age = age;
    }

    
}
