package com.distributedsystems.consumer;

import com.distributedsystems.consumer.model.RequestLogs;
import com.distributedsystems.consumer.repository.LogsRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

@Data
@Component
@NoArgsConstructor
public class LogsConsumer {

    private LogsRepository logsRepository;

    @Autowired
    public LogsConsumer(LogsRepository logsRepository) {
        this.logsRepository = logsRepository;
    }

    public void consumeMessage() {
        Collection<String> topics = new ArrayList<>();
        topics.add("logsTopic");
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("group.id", "test");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(topics);
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                if(record != null) {
                    System.out.println("key: " + record.key() + " value: " + record.value());
                    final RequestLogs requestLogs = RequestLogs.builder().time(record.key()).message(record.value()).build();
                    logsRepository.save(requestLogs);
                }
            }
        }
    }
}
