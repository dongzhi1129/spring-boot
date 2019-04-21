package org.spring.boot.rabbitmq.core;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.spring.boot.rabbitmq.util.RabbitmqUtils;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class RabbitMqServer {

	public static final String default_exchange_name = "hello_exchange";

	public static final String default_queue_name = "hello_queue";

	public static final String default_routing_key = "hello";

	public static void main(String[] args) {
		Connection connection = null;
		Channel channel = null;
		try {
			connection = RabbitmqUtils.getConnection();
			channel = connection.createChannel();
			channel.confirmSelect();
			channel.exchangeDeclare(default_exchange_name, BuiltinExchangeType.DIRECT);
			channel.queueDeclare(default_queue_name, false, false, true, null);
			channel.queueBind(default_queue_name, default_exchange_name, default_routing_key);
			String message = "hello word!";
			channel.basicPublish(default_exchange_name, default_routing_key, null, message.getBytes());
		} catch (IOException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (channel != null) {
				RabbitmqUtils.closeQuietly(channel);
			}
			if (connection != null) {
				RabbitmqUtils.closeQuietly(connection);
			}
		}
	}

}
