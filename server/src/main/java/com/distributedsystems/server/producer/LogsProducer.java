package com.distributedsystems.server.producer;

import com.distributedsystems.server.model.Places;
import com.distributedsystems.server.model.RequestLogs;
import com.distributedsystems.server.util.Constants;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class LogsProducer {

    Properties props = new Properties();

    public void sendLogMessage(RequestLogs requestLogs) {
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        producer.send(new ProducerRecord<>(Constants.LOGS_TOPIC, requestLogs.getTime().toLocalTime().toString(), requestLogs.getMessage()));

        producer.close();
    }
}
