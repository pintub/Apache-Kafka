package com.p2.kafkaproducer;

import com.p2.KafkaStreamApplicationException;
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
    private static String HASH_TAG_KEY ;
    private Producer<String, String> kafkaProducer;
    private String kafkaTopic;
    private TwitterSteamInstanceFactory twitterSteamInstanceFactory;

    public void setTwitterSteamInstanceFactory(TwitterSteamInstanceFactory twitterSteamInstanceFactory) {
        this.twitterSteamInstanceFactory = twitterSteamInstanceFactory;
    }

    public void setKafkaProducer(Producer<String, String> kafkaProducer){
        this.kafkaProducer = kafkaProducer;
    }

    public Producer<String, String> getKafkaProducer(){
        return this.kafkaProducer;
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

    public static void main(String[] args) throws KafkaStreamApplicationException {

        if(args.length == 0){
            throw new KafkaStreamApplicationException("Enter Proper HashTag As Argument");
        } else {
            HASH_TAG_KEY = args[0].toString();
        }

        TwitterHashTagSteamProducer twitterHashTagSteamProducer = new TwitterHashTagSteamProducer();

        twitterHashTagSteamProducer.setTwitterSteamInstanceFactory(new TwitterSteamInstanceFactory(new ConfigurationBuilder()));
        twitterHashTagSteamProducer
                .setKafkaProducer(new KafkaProducer<String, String>(KafkaConfigurationConstants.KAFKA_CONFIG_PROPS));
        twitterHashTagSteamProducer
                .setKafkaTopic(HASH_TAG_KEY);
        try {
            twitterHashTagSteamProducer.initTwitterStream();
        } catch (Exception e){
            twitterHashTagSteamProducer.getKafkaProducer().close();//To free up Kafka Broker connection and resources
            throw new KafkaStreamApplicationException("Exception in Kafka topic Producing", e);
        }
    }
}
