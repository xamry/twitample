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
package com.impetus.kundera.graphbrowser.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.impetus.kundera.graphbrowser.IMDBUtils;
import com.impetus.kundera.graphbrowser.entities.Actor;
import com.impetus.kundera.graphbrowser.entities.Movie;
import com.impetus.kundera.graphbrowser.entities.Role;
import com.impetus.kundera.graphbrowser.service.IMDBService;

/**
 * Managed Bean for IMDB 
 * @author amresh.singh
 */
@ManagedBean(name="imdbBean")
@RequestScoped
public class IMDBBean
{
    public String execute()
    {       
        
        IMDBService imdbService = IMDBUtils.getIMDBService();        
        
        // Actors
        Actor actor1 = new Actor(1, "Tom Cruise");
        Actor actor2 = new Actor(2, "Emmanuelle Béart");

        // Movies
        Movie movie1 = new Movie("m1", "War of the Worlds", 2005);
        Movie movie2 = new Movie("m2", "Mission Impossible", 1996);
        Movie movie3 = new Movie("m3", "Hell", 2009);

        // Roles
        Role role1 = new Role("Ray Ferrier", "Lead Actor");
        role1.setActor(actor1);
        role1.setMovie(movie1);
        Role role2 = new Role("Ethan Hunt", "Lead Actor");
        role2.setActor(actor1);
        role2.setMovie(movie2);
        Role role3 = new Role("Claire Phelps", "Lead Actress");
        role3.setActor(actor2);
        role1.setMovie(movie2);
        Role role4 = new Role("Sophie", "Supporting Actress");
        role4.setActor(actor2);
        role1.setMovie(movie3);

        // Relationships
        actor1.addMovie(role1, movie1);
        actor1.addMovie(role2, movie2);
        actor2.addMovie(role3, movie2);
        actor2.addMovie(role4, movie3);

        movie1.addActor(role1, actor1);
        movie2.addActor(role2, actor1);
        movie2.addActor(role3, actor2);
        movie3.addActor(role4, actor2);
        
        List<Actor> actors = new ArrayList<Actor>();
        actors.add(actor1);
        actors.add(actor2);
        imdbService.insert(actor1, actor2);    
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Test case run successfully"));
        
        imdbService.find();
        
        imdbService.findActorByName();
        
        imdbService.merge();
        
        imdbService.delete();
        
        imdbService.find();
        
        String outcome = "insertionSuccessful";
        
        return outcome;
    }
    
    
    
    protected void populateActors(Actor actor1, Actor actor2)
    {
        // Actors
        actor1 = new Actor(1, "Tom Cruise");
        actor2 = new Actor(2, "Emmanuelle Béart");

        // Movies
        Movie movie1 = new Movie("m1", "War of the Worlds", 2005);
        Movie movie2 = new Movie("m2", "Mission Impossible", 1996);
        Movie movie3 = new Movie("m3", "Hell", 2009);

        // Roles
        Role role1 = new Role("Ray Ferrier", "Lead Actor");
        role1.setActor(actor1);
        role1.setMovie(movie1);
        Role role2 = new Role("Ethan Hunt", "Lead Actor");
        role2.setActor(actor1);
        role2.setMovie(movie2);
        Role role3 = new Role("Claire Phelps", "Lead Actress");
        role3.setActor(actor2);
        role1.setMovie(movie2);
        Role role4 = new Role("Sophie", "Supporting Actress");
        role4.setActor(actor2);
        role1.setMovie(movie3);

        // Relationships
        actor1.addMovie(role1, movie1);
        actor1.addMovie(role2, movie2);
        actor2.addMovie(role3, movie2);
        actor2.addMovie(role4, movie3);

        movie1.addActor(role1, actor1);
        movie2.addActor(role2, actor1);
        movie2.addActor(role3, actor2);
        movie3.addActor(role4, actor2);
    }


}
