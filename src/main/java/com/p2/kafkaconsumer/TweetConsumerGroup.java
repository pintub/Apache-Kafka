package com.p2.kafkaconsumer;

import com.p2.KafkaStreamApplicationException;
import com.p2.constant.KafkaConfigurationConstants;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static javax.swing.text.html.HTML.Tag.TH;

/**
 * Class to read tweets from Kafka topic
 * Input:
 * Number of partitions
 * Topic Name
 * Group.id
 */
public class TweetConsumerGroup {

    private ExecutorService executor ;
    private String topicName ;
    private int noOfPartition ;

    public TweetConsumerGroup(ExecutorService executor, int noOfPartition, String topicName){
        this.executor = executor;
        this.noOfPartition = noOfPartition;
        this.topicName = topicName;
    }

    public void consume(){
        for (int threadNo = 0 ; threadNo < noOfPartition ; threadNo++){
            TweetConsumerRunnable consumerTask = new TweetConsumerRunnable(topicName, threadNo);
            executor.execute(consumerTask);
        }

        executor.shutdown();
    }

    public static void main(String[] args) throws KafkaStreamApplicationException {

        if(args.length != 2){
            throw new KafkaStreamApplicationException("Invalid Program arguments , Expected Arguments patter is <TOPIC> <NO_OF_PARTITION_OF_TOPIC>");
        }

        String topic= args[0];
        int noOfPartition= Integer.parseInt(args[1]);

        ExecutorService executor = Executors.newFixedThreadPool(noOfPartition);
        TweetConsumerGroup consumerGroup = new TweetConsumerGroup(executor, noOfPartition, topic);

        consumerGroup.consume();
    }

}
