package com.p2.kafkaproducer;

import com.p2.KafkaStreamApplicationException;
import com.p2.constant.KafkaConfigurationConstants;
import com.p2.twitter.twitterinstancefactory.TwitterSteamInstanceFactory;
import com.p2.twitter.twitterstreamlistener.TwitterStreamListener;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by I335831 on 5/5/2018.
 * Produces Stream of #HashTag Messages into Kafka Topic
 */
public class TweetProducer {
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

    private Producer<String, String> getKafkaProducer(){
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

    public void produce(){
        this.initTwitterStream();
    }

    private TwitterStreamListener getTwitterStatusListener(){
        return new TwitterStreamListener(this.kafkaProducer, this.kafkaTopic);
    }

    private FilterQuery getFilterQuery(){
        return new FilterQuery().track(HASH_TAG_KEY);
    }

    public static void main(String[] args) throws KafkaStreamApplicationException {

        if(args.length != 1){
            throw new KafkaStreamApplicationException("Enter Proper HashTag As Argument");
        } else {
            HASH_TAG_KEY = args[0].toString();
        }

        TweetProducer tweetProducer = new TweetProducer();

        tweetProducer.setTwitterSteamInstanceFactory(new TwitterSteamInstanceFactory(new ConfigurationBuilder()));
        tweetProducer
                .setKafkaProducer(new KafkaProducer<String, String>(KafkaConfigurationConstants.KAFKA_PRODUCER_CONFIG_PROPS));
        tweetProducer
                .setKafkaTopic(HASH_TAG_KEY);
        try {
            tweetProducer.produce();
        } catch (Exception e){
            tweetProducer.getKafkaProducer().close();//To free up Kafka Broker connection and resources
            System.out.println("Kafka Producer connection Freed-up");
            throw new KafkaStreamApplicationException("Exception while Producing in Kafka topic " , e);
        }
    }
}
