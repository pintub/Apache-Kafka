package com.p2.kafkaproducer;

import com.p2.kafkaproducer.constant.KafkaConfigurationConstants;
import com.p2.kafkaproducer.factory.TwitterSteamInstanceFactory;
import com.p2.kafkaproducer.listener.TwitterStatusListener;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by I335831 on 5/5/2018.
 * Produces Stream of #HashTag Messages into Kafka Topic
 */
public class TwitterHashTagSteamProducer {
    private static final String HASH_TAG_KEY = "IPL2018";
    private Producer<String, String> kafkaProducer;
    private String kafkaTopic;
    private TwitterSteamInstanceFactory twitterSteamInstanceFactory;

    public void setTwitterSteamInstanceFactory(TwitterSteamInstanceFactory twitterSteamInstanceFactory) {
        this.twitterSteamInstanceFactory = twitterSteamInstanceFactory;
    }

    public void setKafkaProducer(Producer<String, String> kafkaProducer){
        this.kafkaProducer = kafkaProducer;
    }

    public void setKafkaTopic(String kafkaTopic){
        this.kafkaTopic = kafkaTopic;
    }

    private void initTwitterStream(){
        TwitterStream twitterStream = twitterSteamInstanceFactory.getInstance();
        twitterStream.addListener(getTwitterStatusListener());
        twitterStream.filter(getFilterQuery());
    }

    private TwitterStatusListener getTwitterStatusListener(){
        return new TwitterStatusListener(this.kafkaProducer, this.kafkaTopic);
    }

    private FilterQuery getFilterQuery(){
        return new FilterQuery().track(HASH_TAG_KEY);
    }

    public static void main(String[] args) {
        TwitterHashTagSteamProducer twitterHashTagSteamProducer = new TwitterHashTagSteamProducer();

        twitterHashTagSteamProducer.setTwitterSteamInstanceFactory(new TwitterSteamInstanceFactory(new ConfigurationBuilder()));
        twitterHashTagSteamProducer
                .setKafkaProducer(new KafkaProducer<String, String>(KafkaConfigurationConstants.KAFKA_CONFIG_PROPS));
        //FIXME
        twitterHashTagSteamProducer
                .setKafkaTopic(HASH_TAG_KEY);

        twitterHashTagSteamProducer.initTwitterStream();
    }
}
