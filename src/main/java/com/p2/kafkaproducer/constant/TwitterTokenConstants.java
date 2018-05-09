package com.p2.kafkaproducer.constant;

import java.util.ResourceBundle;

/**
 * Created by I335831 on 5/5/2018.
 */
public class TwitterTokenConstants {

    public static String CONSUMER_KEY ;
    public static String CONSUMER_SECRET;
    public static String ACCESS_TOKEN;
    public static String ACCESS_TOKEN_SECRET;

    private static final String TWITTER_TOKEN_FILE = "TwitterOauthToken";

    private static ResourceBundle bundle = ResourceBundle.getBundle(TWITTER_TOKEN_FILE);

    static {
        CONSUMER_KEY = bundle.getString("consumerKey");
        CONSUMER_SECRET = bundle.getString("consumerSecret");
        ACCESS_TOKEN = bundle.getString("accessToken");
        ACCESS_TOKEN_SECRET = bundle.getString("accessTokenSecret");
    }
}
