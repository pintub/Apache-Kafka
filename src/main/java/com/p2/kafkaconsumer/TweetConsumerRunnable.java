package com.p2.kafkaconsumer;

import com.p2.constant.KafkaConfigurationConstants;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;

public class TweetConsumerRunnable implements Runnable {

    private final String TOPIC_NAME ;
    private final int threadNo;

    public TweetConsumerRunnable(String topic, int threadNo){
        this.TOPIC_NAME = topic;
        this.threadNo = threadNo;
    }

    @Override
    public void run() {
        KafkaConsumer<String, String> kafkaConsumer =
                new KafkaConsumer<String, String>(KafkaConfigurationConstants.KAFKA_CONSUMER_CONFIG_PROPS);
        kafkaConsumer.subscribe(Arrays.asList(TOPIC_NAME));

        while (true){
            ConsumerRecords<String, String > tweets = kafkaConsumer.poll(10000);
            for(ConsumerRecord<String, String> tweet : tweets){
                System.out.printf("Thread%s - Tweet read from %s topic : " , threadNo, TOPIC_NAME);
                System.out.println("\n");
                System.out.printf("Thread%s - Tweet offset = %d, key = %s, value = %s ", threadNo,
                        tweet.offset(), tweet.key(), tweet.value());
                System.out.println("\n");
            }
        }
    }
}
