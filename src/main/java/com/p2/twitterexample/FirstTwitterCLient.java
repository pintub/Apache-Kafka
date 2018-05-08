package com.p2.twitterexample;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by I335831 on 5/5/2018.
 */
public class FirstTwitterCLient {

    private static String consumerKey ;
    private static String consumerSecret ;
    private static String accessToken ;
    private static String accessTokenSecret ;

    public static void main(String[] args) throws TwitterException {
        init();

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);

        TwitterFactory factory = new TwitterFactory(configurationBuilder.build());
        Twitter twitter = factory.getInstance();

        List<Status> statusList = twitter.getHomeTimeline();
        for(Status status : statusList){
            System.out.println(status.getText() + " by " + status.getUser().getName());
        }
    }

    public static void init(){
        ResourceBundle rb = ResourceBundle.getBundle("TwitterUserToken");
        consumerKey = rb.getString("consumerKey");
        consumerSecret = rb.getString("consumerSecret");
        accessToken = rb.getString("accessToken");
        accessTokenSecret = rb.getString("accessTokenSecret");
    }
}
