/**
 * Copyright 2012 Impetus Infotech.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.impetus.kundera.graphbrowser.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceContexts;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.impetus.kundera.graphbrowser.entities.Actor;

/**
 * <Prove description of functionality provided by this Type>
 * 
 * @author amresh.singh
 */
@Transactional(propagation = Propagation.REQUIRED)
public class IMDBService
{
    @PersistenceContext(unitName = "graph-browser-neo4j,graph-browser-cassandra", type = PersistenceContextType.EXTENDED)
    private EntityManager em;

//    @PersistenceContext(unitName = "graph-browser-neo4j", type = PersistenceContextType.EXTENDED)
//    private EntityManager em1;


    public void insert(Actor actor1, Actor actor2)
    {

        em.persist(actor1);
        em.persist(actor2);
    }

    public void find()
    {
        // Find actor by ID
        em.clear();

        Actor actor1 = em.find(Actor.class, 1);
        Actor actor2 = em.find(Actor.class, 2);
        
       
        System.out.println(actor1 + "            " + actor2);
    }
    
    public void findActorByName()
    {
        Query query = em.createQuery("select a from Actor a where a.name=:name");
        query.setParameter("name", "Tom Cruise");
        List<Actor> actors = query.getResultList();
        System.out.println(actors);
    }

    public void merge()
    {
        Actor actor1 = em.find(Actor.class, 1);
        Actor actor2 = em.find(Actor.class, 2);

        actor1.setName("Amresh");
        actor2.setName("Amir");

        em.merge(actor1);
        em.merge(actor2);

        em.clear();

        Actor actor1AfterMerge = em.find(Actor.class, 1);
        Actor actor2AfterMerge = em.find(Actor.class, 2);
        
        System.out.println(actor1AfterMerge + "        " + actor2AfterMerge);
    }

    public void delete()
    {
        Actor actor1 = em.find(Actor.class, 1);
        Actor actor2 = em.find(Actor.class, 2);

        em.remove(actor1);
        em.remove(actor2);
        
        System.out.println("After Deletion");
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

   /* *//**
     * @return the em1
     *//*
    public EntityManager getEm1()
    {
        return em1;
    }

    *//**
     * @param em1 the em1 to set
     *//*
    public void setEm1(EntityManager em1)
    {
        this.em1 = em1;
    }       */

    
}
