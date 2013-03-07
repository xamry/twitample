/*
 * Copyright 2011 Impetus Infotech.
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
package com.impetus.twitample.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.impetus.twitample.entities.ExternalLink;
import com.impetus.twitample.entities.Preference;
import com.impetus.twitample.entities.Tweet;
import com.impetus.twitample.entities.User;

/**
 * Data access object class for implementation of twitter.
 * 
 * @author amresh.singh
 */

@Transactional(propagation = Propagation.REQUIRED)
public class TwitterService extends SuperDao implements Twitter
{
    @PersistenceContext(unitName="twitample_cassandra", type=PersistenceContextType.EXTENDED)
    private EntityManager em;   

   
    @Override
    public void addUser(User user)
    {
        em.persist(user);
    }

    @Override
    public void addUser(String userId, String name, String password, String relationshipStatus)
    {
        User user = new User(userId, name, password, relationshipStatus);
        em.persist(user);

    }

    @Override
    public void savePreference(String userId, Preference preference)
    {

        User user = em.find(User.class, userId);
        user.setPreference(preference);
        em.persist(user);
    }

    @Override
    public void addExternalLink(String userId, String linkId, String linkType, String linkAddress)
    {
        User user = em.find(User.class, userId);
        user.addExternalLink(new ExternalLink(linkId, linkType, linkAddress));

        em.persist(user);
    }

    @Override
    public void addTweet(String userId, String tweetBody, String device)
    {
        User user = em.find(User.class, userId);
        user.addTweet(new Tweet(tweetBody, device));
        em.persist(user);
    }

    @Override
    public void startFollowing(String userId, String friendUserId)
    {
        User user = em.find(User.class, userId);
        User friend = em.find(User.class, friendUserId);

        user.addFriend(friend);
        em.persist(user);

        friend.addFollower(user);
        em.persist(friend);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addFollower(String userId, String followerUserId)
    {
        User user = em.find(User.class, userId);
        User follower = em.find(User.class, followerUserId);

        user.addFollower(follower);
        em.persist(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User findUserById(String userId)
    {
        User user = em.find(User.class, userId);
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeUser(User user)
    {
        em.remove(user);
    }

    @Override
    public void mergeUser(User user)
    {
        em.merge(user);
    }

    @Override
    public List<User> getAllUsers()
    {

        Query q = em.createQuery("select u from User u");

        List<User> users = q.getResultList();

        return users;
    }

    @Override
    public List<Tweet> getAllTweets(String userId)
    {
        Query q = em.createQuery("select u from User u where u.userId =:userId");
        q.setParameter("userId", userId);
        List<User> users = q.getResultList();
        if (users == null || users.isEmpty())
        {
            return null;
        }
        else
        {
            return users.get(0).getTweets();
        }
    }

    @Override
    public List<User> getFollowers(String userId)
    {
        Query q = em.createQuery("select u from User u where u.userId =:userId");
        q.setParameter("userId", userId);
        List<User> users = q.getResultList();
        if (users == null || users.isEmpty())
        {
            return null;
        }
        return users.get(0).getFollowers();
    }
    
    @Override
    public List<User> findPersonalDetailByName(String name)
    {
        Query q = em.createQuery("select u.personalDetail.name from User u where u.personalDetail.name =:name");
        q.setParameter("name", name);
        List<User> users = q.getResultList();        
        return users;        
    }

    @Override
    public List<Tweet> findTweetByBody(String tweetBody)
    {
        Query q = em.createQuery("select u.tweets.body from User u where u.tweets.body =:body");
        q.setParameter("body", tweetBody);
        List<Tweet> tweets = q.getResultList();
        return tweets;
    }

    @Override
    public List<Tweet> findTweetByDevice(String deviceName)
    {
        Query q = em.createQuery("select u.tweets.device from User u where u.tweets.device =:device");
        q.setParameter("device", deviceName);
        List<Tweet> tweets = q.getResultList();
        return tweets;
    }
}
