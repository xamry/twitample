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

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.impetus.twitample.Constants;
import com.impetus.twitample.TwitampleUtils;
import com.impetus.twitample.entities.Tweet;
import com.impetus.twitample.entities.User;
import com.impetus.twitample.service.Twitter;

/**
 * <Prove description of functionality provided by this Type> 
 * @author amresh.singh
 */

@ManagedBean
@RequestScoped
public class TimelineBean
{    
    Twitter twitter;
    
    private String tweetBody;

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
            
            User user = getTwitter().findUserById(userId);
            user.addTweet(new Tweet(getTweetBody(), Constants.TWEET_DEVICE_WEB));
            
            getTwitter().mergeUser(user);
            
            outcome = Constants.OUTCOME_TIMELINE;
            return outcome;
        }      
        
    } 
    

}
