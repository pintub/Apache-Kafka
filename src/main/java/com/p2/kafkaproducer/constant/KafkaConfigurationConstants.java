package com.p2.kafkaproducer.constant;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by I335831 on 5/5/2018.
 */
public class KafkaConfigurationConstants {

    private static final String KAFKA_CONFIG_FILE = "KafkaConfiguration";
    public static Properties KAFKA_CONFIG_PROPS = new Properties();

    static {
        ResourceBundle kafkaBundle = ResourceBundle.getBundle(KAFKA_CONFIG_FILE);
        KAFKA_CONFIG_PROPS.put("bootstrap.servers", kafkaBundle.getString("bootstrap.servers"));
        KAFKA_CONFIG_PROPS.put("acks", kafkaBundle.getString("acks"));
        KAFKA_CONFIG_PROPS.put("retries", Integer.parseInt(kafkaBundle.getString("retries")));
        KAFKA_CONFIG_PROPS.put("batch.size", Integer.parseInt(kafkaBundle.getString("batch.size")));
        KAFKA_CONFIG_PROPS.put("linger.ms", Integer.parseInt(kafkaBundle.getString("linger.ms")));
        KAFKA_CONFIG_PROPS.put("buffer.memory", Integer.parseInt(kafkaBundle.getString("buffer.memory")));
        KAFKA_CONFIG_PROPS.put("key.serializer", kafkaBundle.getString("key.serializer"));
        KAFKA_CONFIG_PROPS.put("value.serializer", kafkaBundle.getString("value.serializer"));
    }
}
