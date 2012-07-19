package com.impetus.apps.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * create column family PERSONNEL with comparator=AsciiType and
      key_validation_class=UTF8Type and column_metadata=[{column_name:
       PERSON_NAME, validation_class:UTF8Type, index_type: KEYS},
       {column_name: AGE, validation_class:IntegerType, index_type: KEYS},
{column_name:
       EMAIL_ID, validation_class:UTF8Type, index_type: KEYS}
];
 * @author vivek.mishra
 *
 */
@Entity
@Table(name = "PERSONNEL", schema = "KunderaTests@personnel_cass_pu")
@NamedQuery(query="Select p PersonnelEntity p where p.emailId= :emailId", name="findby.email.query")
public class PersonnelEntity
{
    @Id
    @Column(name = "PERSON_ID")
    private String personId;

    @Column(name = "PERSON_NAME")
    private String personName;
    
    @Column(name="EMAIL_ID")
    private String emailId;
    
    @Column(name="AGE")
    private int age;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    private AddressEntity address;

    
    /**
     * 
     */
    public PersonnelEntity()
    {
    }

    public String getPersonId()
    {
        return personId;
    }

    public String getPersonName()
    {
        return personName;
    }

    public void setPersonName(String personName)
    {
        this.personName = personName;
    }

    public void setPersonId(String personId)
    {
        this.personId = personId;
    }

    public AddressEntity getAddress()
    {
        return address;
    }

    public void setAddress(AddressEntity address)
    {
        this.address = address;
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
