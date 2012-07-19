package com.impetus.apps.service;


/**
 * The Interface Dao. All Dao instances will implement this interface and Each Dao is bound to deal with specific {@link Referencable} entity.
 *
 * @param <R> the generic type
 * @author vivek.mishra
 */
public interface Dao<R extends Object>
{

    /**
     * Persist.
     *
     * @param ob the ob
     */
    void persist(R ob);
    
    /**
     * Removes the.
     *
     * @param obj the obj
     */
    void remove(R obj);
    
    /**
     * Find.
     *
     * @param clazz the clazz
     * @param primaryKey the primary key
     */
    R find(Class<R> clazz, Object primaryKey);
    
    /**
     * Update.
     *
     * @param obj the obj
     */
    void update(R obj);
    
    void flush();
    
    void clear();
}
