package com.distributedsystems.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.distributedsystems.consumer.repository")
@SpringBootApplication
public class ConsumerApplication {

	static LogsConsumer logsConsumer;

	public ConsumerApplication(LogsConsumer logsConsumer) {
		this.logsConsumer = logsConsumer;
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
		logsConsumer.consumeMessage();
	}
}
