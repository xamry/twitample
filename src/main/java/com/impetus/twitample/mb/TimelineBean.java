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
package com.impetus.twitample.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.impetus.twitample.Constants;
import com.impetus.twitample.TwitampleUtils;
import com.impetus.twitample.dto.TweetRow;
import com.impetus.twitample.entities.Tweet;
import com.impetus.twitample.entities.User;
import com.impetus.twitample.service.Twitter;

/**
 * <Prove description of functionality provided by this Type> 
 * @author amresh.singh
 */

@ManagedBean(name="timelineBean")
@RequestScoped
public class TimelineBean
{    
    Twitter twitter;
    
    private String tweetBody;
    
    private List<TweetRow> tweetList;  
    private TweetRow selectedTweet;
    
    private String searchCriteriaBody;
    
    
    private List<User> allUsers;

    /**
     * @return the tweetBody
     */
    public String getTweetBody()
    {
        return tweetBody;
    }

    /**
     * @param tweetBody the tweetBody to set
     */
    public void setTweetBody(String tweetBody)
    {
        this.tweetBody = tweetBody;
    }

    /**
     * @return the twitter
     */
    public Twitter getTwitter()
    {
        return twitter;
    }

    /**
     * @param twitter the twitter to set
     */
    public void setTwitter(Twitter twitter)
    {
        this.twitter = twitter;
    } 
    
    
    /**
     * @return the selectedTweet
     */
    public TweetRow getSelectedTweet()
    {
        return selectedTweet;
    }

    /**
     * @param selectedTweet the selectedTweet to set
     */
    public void setSelectedTweet(TweetRow selectedTweet)
    {
        this.selectedTweet = selectedTweet;
    }  
    

    /**
     * @return the searchCriteriaBody
     */
    public String getSearchCriteriaBody()
    {
        return searchCriteriaBody;
    }

    /**
     * @param searchCriteriaBody the searchCriteriaBody to set
     */
    public void setSearchCriteriaBody(String searchCriteriaBody)
    {
        this.searchCriteriaBody = searchCriteriaBody;
    }

    /**
     * @return the allUsers
     */
    public List<User> getAllUsers()
    {
        allUsers = new ArrayList<User>();
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        setTwitter(TwitampleUtils.getTwitterService());                
        List<User> users = getTwitter().getAllUsers();
        
        if(users != null && !users.isEmpty()) {
            setAllUsers(users);
        }
        
        return allUsers;
    }

    /**
     * @param allUsers the allUsers to set
     */
    public void setAllUsers(List<User> allUsers)
    {
        this.allUsers = allUsers;
    }

    /**
     * @return the tweetList
     */
    public List<TweetRow> getTweetList()
    {
        
        tweetList = new ArrayList<TweetRow>();
        
        if(StringUtils.isNotEmpty(getSearchCriteriaBody())) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            setTwitter(TwitampleUtils.getTwitterService());
            String userId = (String)session.getAttribute(Constants.USER_ID);
            
            List users = getTwitter().findTweetByBody(getSearchCriteriaBody());
            
            if(users == null || users.isEmpty()) {
                return tweetList;
            }
            
            for(User user : (List<User>)users) {
                List<Tweet> tweets = user.getTweets();
                if(tweets != null) {
                    
                    for(Tweet tweet : tweets) {
                        if(tweet != null && tweet.getBody() != null) {
                            TweetRow tr = new TweetRow();
                            tr.setId(tweet.getTweetId());
                            tr.setBody(tweet.getBody());
                            tr.setDevice(tweet.getDevice());
                            tr.setName(userId);
                            
                            tweetList.add(tr);
                        }
                    }      
                    
                }
            }
            
              
        } else {
          //Get all tweets for this user
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            setTwitter(TwitampleUtils.getTwitterService());
            String userId = (String)session.getAttribute(Constants.USER_ID);
            
            //Find user and update tweet list       
            User user = getTwitter().findUserById(userId);
            if(user.getTweets() != null) {
                for(Tweet tweet : user.getTweets()) {
                    TweetRow tr = new TweetRow();
                    tr.setId(tweet.getTweetId());
                    tr.setName(user.getPersonalDetail().getName());
                    tr.setBody(tweet.getBody());
                    tr.setDevice(tweet.getDevice());
                    
                    tweetList.add(tr);
                }
            }  
        }             
        return tweetList;
    }

    /**
     * @param tweetList the tweetList to set
     */
    public void setTweetList(List<TweetRow> tweetList)
    {
        this.tweetList = tweetList;
    }
    
    public String searchByBody() {
        String outcome = null;
        List<TweetRow> searchResults = new ArrayList<TweetRow>();
        
        // Validates Parameters
        if (StringUtils.isBlank(getSearchCriteriaBody()))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please enter a text to search"));
           
        }        
        outcome = Constants.OUTCOME_TIMELINE;
       
        return outcome;
    }

    public String saveTweet() {
        String outcome = null;       

        // Validates Parameters
        if (StringUtils.isBlank(getTweetBody()))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please enter tweet body"));
            outcome = Constants.OUTCOME_TIMELINE;
        }
        
        if (StringUtils.isNotBlank(outcome))
        {
            return outcome;
        } else {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            setTwitter(TwitampleUtils.getTwitterService());
            String userId = (String)session.getAttribute(Constants.USER_ID);
            
            //Find user and update tweet
            User user = getTwitter().findUserById(userId);
            user.addTweet(new Tweet(getTweetBody(), Constants.TWEET_DEVICE_WEB));           
            getTwitter().mergeUser(user);
            
            
            outcome = Constants.OUTCOME_TIMELINE;
            return outcome;
        }      
        
    } 
    
    public void deleteTweet() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        setTwitter(TwitampleUtils.getTwitterService());
        String userId = (String)session.getAttribute(Constants.USER_ID);
        
        //Find user and update tweet
        User user = getTwitter().findUserById(userId);
        
        Tweet tweet = new Tweet();
        tweet.setTweetId(selectedTweet.getId());
        tweet.setBody(selectedTweet.getBody());
        tweet.setDevice(selectedTweet.getDevice());        
        user.getTweets().remove(tweet);
        getTwitter().mergeUser(user);
        
        getTweetList().remove(selectedTweet);
    }
    

}
