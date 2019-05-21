package com.learn.stream.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableBinding(Source.class)
@EnableScheduling
public class Producer {

	@Autowired
	private Source source;

	@Scheduled(fixedRate = 10000)
	@SendTo(Source.OUTPUT)
	public void sendMessage() {
		long message = System.currentTimeMillis();
		log.info("Producer Send [{}] to channel [{}]",message,Source.OUTPUT);
		source.output().send(MessageBuilder.withPayload(message).build());

	}

}
