package com.learn.stream.consumer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class Consumer {
	
	@StreamListener(Sink.INPUT)
	public void receiveMessage(Object payload) {
		System.out.println(payload);
	}

}
