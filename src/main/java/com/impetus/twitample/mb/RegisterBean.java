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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FlowEvent;

import com.impetus.twitample.TwitampleUtils;
import com.impetus.twitample.entities.PersonalDetail;
import com.impetus.twitample.entities.Preference;
import com.impetus.twitample.entities.User;
import com.impetus.twitample.service.Twitter;

/**
 * <Prove description of functionality provided by this Type> 
 * @author amresh.singh
 */

@ManagedBean(name="registerBean")
@SessionScoped
public class RegisterBean
{
    
    Twitter twitter;
    
    public RegisterBean() {
        user = new User();
        user.setPersonalDetail(new PersonalDetail());
        Preference pref = new Preference();
        pref.setPreferenceId(TwitampleUtils.getUniqueId());
        user.setPreference(pref);
    }
    
    private User user = new User();
    private boolean skip;  

    /**
     * @return the user
     */
    public User getUser()
    {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user)
    {
        this.user = user;
    } 
    
    
    /**
     * @return the skip
     */
    public boolean isSkip()
    {
        return skip;
    }

    /**
     * @param skip the skip to set
     */
    public void setSkip(boolean skip)
    {
        this.skip = skip;
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

    public String onFlowProcess(FlowEvent event) {  
        
          
        if(skip) {  
            skip = false;   //reset in case user goes back  
            return "confirm";  
        }  
        else {  
            return event.getNewStep();  
        }  
    }  
    
    public String save() {  
        setTwitter(TwitampleUtils.getTwitterService());
        
        twitter.addUser(user);
          
        FacesMessage msg = new FacesMessage("SignUp Successful! Welcome, " + user.getPersonalDetail().getName());  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        
        
        return "signUpSuccessful";
    }    

}
