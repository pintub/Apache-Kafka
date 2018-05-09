package com.p2.kafkaproducer.listener;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import twitter4j.*;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by I335831 on 5/5/2018.
 */
public class TwitterStatusListener implements StatusListener {
    private final Producer<String, String> kafkaProducer;

    private final String kafkaTopic;

    public TwitterStatusListener(Producer<String, String> kafkaProducer, String kafkaTopic) {
        this.kafkaProducer = kafkaProducer;
        this.kafkaTopic = kafkaTopic;
    }

    @Override
    public void onStatus(Status status) {
        System.out.println("Tweet with HashTag : " + kafkaTopic +"\n" );
        System.out.println("==============================================" +"\n" );
        System.out.println(status.getText() + " by " + status.getUser().getName());
        System.out.println("\n");
        System.out.println("HashTags\n");
        for(HashtagEntity hashtagEntity : status.getHashtagEntities()) {
            System.out.println(hashtagEntity.getText());
        }

        kafkaProducer.send(new ProducerRecord<String, String>(kafkaTopic, null
            , status.getUser().getName() + " ::: " + status.getText()));
        System.out.println("Tweet pushed with HashTag " + kafkaTopic + " pushed to Kafka Topic");
        System.out.println("==============================================" +"\n" );
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
        //TODO
    }

    @Override
    public void onTrackLimitationNotice(int i) {
        //TODO
    }

    @Override
    public void onScrubGeo(long l, long l1) {
        //TODO
    }

    @Override
    public void onStallWarning(StallWarning stallWarning) {
        //TODO
    }

    @Override
    public void onException(Exception e) {
        e.printStackTrace();
    }
}
