package com.learn.stream.consumer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;

import lombok.extern.slf4j.Slf4j;

@EnableBinding(value = Sink.class)
@Slf4j
public class IntegrationConsumer {

	@ServiceActivator(inputChannel = Sink.INPUT)
	public void receiveMessage(Object message) {

		log.info("Receive Message[{}] From Topic [{}]", message, Sink.INPUT);
	}

}
