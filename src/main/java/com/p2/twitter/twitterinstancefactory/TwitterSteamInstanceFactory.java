package com.p2.twitter.twitterinstancefactory;

import com.p2.constant.TwitterTokenConstants;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterSteamInstanceFactory {

    private ConfigurationBuilder configurationBuilder ;

    public TwitterSteamInstanceFactory(ConfigurationBuilder configurationBuilder){
        this.configurationBuilder = configurationBuilder;
    }

    public TwitterStream getInstance(){
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(TwitterTokenConstants.CONSUMER_KEY)
                .setOAuthConsumerSecret(TwitterTokenConstants.CONSUMER_SECRET)
                .setOAuthAccessToken(TwitterTokenConstants.ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(TwitterTokenConstants.ACCESS_TOKEN_SECRET);

        return new TwitterStreamFactory(configurationBuilder.build()).getInstance();
    }
}
