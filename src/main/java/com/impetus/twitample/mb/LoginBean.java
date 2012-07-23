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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.impetus.twitample.Constants;
import com.impetus.twitample.TwitampleUtils;
import com.impetus.twitample.entities.User;
import com.impetus.twitample.service.Twitter;




/**
 * <Prove description of functionality provided by this Type> 
 * @author amresh.singh
 */

@ManagedBean(name="loginBean")
@RequestScoped
public class LoginBean
{
    private String userName;
    private String password;   
    
    Twitter twitter;
    
    public String logOff() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You have successfully Logged off"));
        session.invalidate();
        return Constants.OUTCOME_SIGNUP_SUCCESSFUL;
    }
    
    public String deleteAccount() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        setTwitter(TwitampleUtils.getTwitterService());
        String userId = (String)session.getAttribute(Constants.USER_ID);
        
        User user = getTwitter().findUserById(userId);
        
        getTwitter().removeUser(user);
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Account successfully deleted"));
        session.invalidate();
        return Constants.OUTCOME_SIGNUP_SUCCESSFUL;
    }
    
    public String authenticate()
    {
        String outcome = null;

        

        // Validates Parameters
        if (StringUtils.isBlank(getUserName()))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please enter your user name"));
            outcome = Constants.OUTCOME_LOGIN_FAILED;
        }

        if (StringUtils.isBlank(getPassword()))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please enter password"));
            outcome = Constants.OUTCOME_LOGIN_FAILED;
        }

        if (StringUtils.isNotBlank(outcome))
        {
            return outcome;
        }
        else
        {   
            setTwitter(TwitampleUtils.getTwitterService());                       
            
            User user = twitter.findUserById(getUserName());            
            
            if(user == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Incorrect User Name"));
                outcome = Constants.OUTCOME_LOGIN_FAILED;
            } else if(user.getPersonalDetail().getPassword() == null || ! user.getPersonalDetail().getPassword().equals(getPassword())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Incorrect Password"));
                outcome = Constants.OUTCOME_LOGIN_FAILED;
            }

            if (StringUtils.isNotBlank(outcome))
            {
                return outcome;
            } else {
                outcome = Constants.OUTCOME_LOGIN_SUCCESSFUL;
            }      
            
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute(Constants.USER_ID, getUserName());
            
            return outcome;
        }
    }

    /**
     * @return the userName
     */
    public String getUserName()
    {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
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
    

}
