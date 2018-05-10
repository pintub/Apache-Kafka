package com.p2.constant;

import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by I335831 on 5/5/2018.
 */
public class KafkaConfigurationConstants {

    private static final String KAFKA_PRODUCER_CONFIG_FILE = "KafkaProducerConfiguration";
    private static final String KAFKA_CONSUMER_CONFIG_FILE = "KafkaConsumerConfiguration";
    public static Properties KAFKA_PRODUCER_CONFIG_PROPS = new Properties();
    public static Properties KAFKA_CONSUMER_CONFIG_PROPS = new Properties();

    static {
        ResourceBundle kafkaProducerConfigBundle = ResourceBundle.getBundle(KAFKA_PRODUCER_CONFIG_FILE);
        KAFKA_PRODUCER_CONFIG_PROPS.put("bootstrap.servers", kafkaProducerConfigBundle.getString("bootstrap.servers"));
        KAFKA_PRODUCER_CONFIG_PROPS.put("producer.type", kafkaProducerConfigBundle.getString("producer.type"));
        KAFKA_PRODUCER_CONFIG_PROPS.put("acks", kafkaProducerConfigBundle.getString("acks"));
        KAFKA_PRODUCER_CONFIG_PROPS.put("retries", Integer.parseInt(kafkaProducerConfigBundle.getString("retries")));
        KAFKA_PRODUCER_CONFIG_PROPS.put("batch.size", Integer.parseInt(kafkaProducerConfigBundle.getString("batch.size")));
        KAFKA_PRODUCER_CONFIG_PROPS.put("linger.ms", Integer.parseInt(kafkaProducerConfigBundle.getString("linger.ms")));
        KAFKA_PRODUCER_CONFIG_PROPS.put("buffer.memory", Integer.parseInt(kafkaProducerConfigBundle.getString("buffer.memory")));
        KAFKA_PRODUCER_CONFIG_PROPS.put("key.serializer", kafkaProducerConfigBundle.getString("key.serializer"));
        KAFKA_PRODUCER_CONFIG_PROPS.put("value.serializer", kafkaProducerConfigBundle.getString("value.serializer"));


        ResourceBundle kafkaConsumerConfigBundle = ResourceBundle.getBundle(KAFKA_CONSUMER_CONFIG_FILE);
        KAFKA_CONSUMER_CONFIG_PROPS.put("bootstrap.servers", kafkaConsumerConfigBundle.getString("bootstrap.servers"));
        KAFKA_CONSUMER_CONFIG_PROPS.put("group.id", kafkaConsumerConfigBundle.getString("group.id"));
        KAFKA_CONSUMER_CONFIG_PROPS.put("enable.auto.commit", Boolean.parseBoolean(kafkaConsumerConfigBundle.getString("enable.auto.commit")));
        KAFKA_CONSUMER_CONFIG_PROPS.put("auto.commit.interval.ms", Integer.parseInt(kafkaConsumerConfigBundle.getString("auto.commit.interval.ms")));
        KAFKA_CONSUMER_CONFIG_PROPS.put("session.timeout.ms", Integer.parseInt(kafkaConsumerConfigBundle.getString("session.timeout.ms")));
        KAFKA_CONSUMER_CONFIG_PROPS.put("key.deserializer", kafkaConsumerConfigBundle.getString("key.deserializer"));
        KAFKA_CONSUMER_CONFIG_PROPS.put("value.deserializer", kafkaConsumerConfigBundle.getString("value.deserializer"));
    }
}
