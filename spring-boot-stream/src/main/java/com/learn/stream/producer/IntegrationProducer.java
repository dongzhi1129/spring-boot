package com.learn.stream.producer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

@EnableBinding(value = {Source.class})
public class IntegrationProducer {
	
	@InboundChannelAdapter(channel = Source.OUTPUT,poller = {@Poller(fixedRate = "2000")} )
	public Message<Date> send() {
		
		return new GenericMessage<>(new Date());
	}
	
	@Transformer(inputChannel = Sink.INPUT,outputChannel = Source.OUTPUT)
	public Object transform(Date message) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss").format(message);
		
		
	}

}
