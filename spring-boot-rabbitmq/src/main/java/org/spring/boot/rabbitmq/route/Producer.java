package org.spring.boot.rabbitmq.route;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeoutException;

import org.spring.boot.rabbitmq.util.RabbitmqUtils;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer {
	
	public static String DEFAULT_ROUTE_EXCHANGE = "default_route_exchange";
	
	public static String DEFAULT_ERROR_QUEUE = "default_error_queue";
	
	public static String DEFAULT_OHTER_LEVEL_QUEUE = "other_level_queue";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = RabbitmqUtils.getConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(DEFAULT_ROUTE_EXCHANGE, BuiltinExchangeType.DIRECT);
		channel.queueDeclare(DEFAULT_ERROR_QUEUE, false, false, false, null);
		channel.queueDeclare(DEFAULT_OHTER_LEVEL_QUEUE, false, false, false, null);
		channel.queueBind(DEFAULT_ERROR_QUEUE, DEFAULT_ROUTE_EXCHANGE, "error");
		channel.queueBind(DEFAULT_OHTER_LEVEL_QUEUE,DEFAULT_ROUTE_EXCHANGE,"info");
		channel.queueBind(DEFAULT_OHTER_LEVEL_QUEUE, DEFAULT_ROUTE_EXCHANGE, "warning");
		while(true) {
			Random random =new Random(1);
			int i = random.nextInt();
			if(i == 0) {
				channel.basicPublish(DEFAULT_ROUTE_EXCHANGE, "error", null, "erro".getBytes());
			}
			else {
				channel.basicPublish(DEFAULT_ROUTE_EXCHANGE, "info", null, "info".getBytes());
			}
		
		}
	}
	

}
