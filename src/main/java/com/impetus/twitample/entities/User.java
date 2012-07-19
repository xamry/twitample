/**
 * 
 */
package com.impetus.twitample.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author impetus
 * 
 */
@Entity
@Table(name = "USER", schema = "twitample@twitample_cassandra")
public class User
{

    @Id
    @Column(name = "USER_ID")
    private String userId;

    // Embedded object, will persist co-located
    @Embedded
    private PersonalDetail personalDetail;

    // Element collection, will persist co-located
    @ElementCollection
    @CollectionTable(name = "tweeted")
    private List<Tweet> tweets;

    // One to many, will be persisted separately
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "FRIEND_ID")
    private List<User> friends; // List of users whom I follow

    // One to many, will be persisted separately
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "FOLLOWER_ID")
    private List<User> followers; // List of users who are following me

    // One-to-one, will be persisted separately
    @OneToOne(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "PREFERENCE_ID")
    private Preference preference;

    // One to many, will be persisted separately
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private Set<ExternalLink> externalLinks;

    public User()
    {

    }

    public User(String userId, String name, String password, String relationshipStatus)
    {
        PersonalDetail pd = new PersonalDetail(name, password, relationshipStatus);
        setUserId(userId);
        setPersonalDetail(pd);
    }

    /**
     * @return the userId
     */
    public String getUserId()
    {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    /**
     * @return the personalDetail
     */
    public PersonalDetail getPersonalDetail()
    {
        return personalDetail;
    }

    /**
     * @param personalDetail
     *            the personalDetail to set
     */
    public void setPersonalDetail(PersonalDetail personalDetail)
    {
        this.personalDetail = personalDetail;
    }

    /**
     * @return the tweets
     */
    public List<Tweet> getTweets()
    {
        return tweets;
    }

    /**
     * @param tweets
     *            the tweets to set
     */
    public void addTweet(Tweet tweet)
    {
        if (this.tweets == null || this.tweets.isEmpty())
        {
            this.tweets = new ArrayList<Tweet>();
        }
        this.tweets.add(tweet);
    }

    /**
     * @return the preference
     */
    public Preference getPreference()
    {
        return preference;
    }

    /**
     * @param preference
     *            the preference to set
     */
    public void setPreference(Preference preference)
    {
        this.preference = preference;
    }

    /**
     * @return the externalLinks
     */
    public Set<ExternalLink> getExternalLinks()
    {
        return externalLinks;
    }

    /**
     * @param imDetails
     *            the imDetails to set
     */
    public void addExternalLink(ExternalLink externalLink)
    {
        if (this.externalLinks == null || this.externalLinks.isEmpty())
        {
            this.externalLinks = new HashSet<ExternalLink>();
        }

        this.externalLinks.add(externalLink);
    }

    /**
     * @return the friends
     */
    public List<User> getFriends()
    {
        return friends;
    }

    /**
     * @param friends
     *            the friends to set
     */
    public void addFriend(User friend)
    {
        if (this.friends == null || this.friends.isEmpty())
        {
            this.friends = new ArrayList<User>();
        }
        this.friends.add(friend);
    }

    /**
     * @return the followers
     */
    public List<User> getFollowers()
    {
        return followers;
    }

    /**
     * @param followers
     *            the followers to set
     */
    public void addFollower(User follower)
    {
        if (this.followers == null || this.followers.isEmpty())
        {
            this.followers = new ArrayList<User>();
        }

        this.followers.add(follower);
    }

}
