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
package com.impetus.twitample.dto;

/**
 * <Prove description of functionality provided by this Type> 
 * @author amresh.singh
 */
public class TweetRow
{
    private String id;
    private String name;
    private String body;
    private String device;    
    
    
    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id)
    {
        this.id = id;
    }
    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }
    /**
     * @return the body
     */
    public String getBody()
    {
        return body;
    }
    /**
     * @param body the body to set
     */
    public void setBody(String body)
    {
        this.body = body;
    }
    /**
     * @return the device
     */
    public String getDevice()
    {
        return device;
    }
    /**
     * @param device the device to set
     */
    public void setDevice(String device)
    {
        this.device = device;
    }  
    
}
