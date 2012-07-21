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
package com.impetus.twitample;

import java.util.Date;
import java.util.UUID;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.impetus.twitample.service.Twitter;

/**
 * <Prove description of functionality provided by this Type> 
 * @author amresh.singh
 */
public class TwitampleUtils
{
    public static String getUniqueId()
    {
        return UUID.randomUUID().toString();
    }

    public static long getCurrentTimestamp()
    {
        return new Date().getTime();
    }
    
    public static Twitter getTwitterService() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        Twitter twitter = (Twitter)session.getAttribute("twitter"); 
        if(twitter == null) {
            BeanFactory beanfactory = new ClassPathXmlApplicationContext("appContext.xml");
            twitter = (Twitter) beanfactory.getBean("twitter");
            session.setAttribute("twitter", twitter);
        }
        return twitter;
    }

}
