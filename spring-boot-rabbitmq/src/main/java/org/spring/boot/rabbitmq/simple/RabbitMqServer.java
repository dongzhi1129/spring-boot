package org.spring.boot.rabbitmq.simple;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.spring.boot.rabbitmq.util.RabbitmqUtils;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class RabbitMqServer {


	public static final String default_queue_name = "hello_queue";

	public static void main(String[] args) {
		Connection connection = null;
		Channel channel = null;
		try {
			connection = RabbitmqUtils.getConnection();
			channel = connection.createChannel();
			channel.queueDeclare(default_queue_name, false, false, true, null);
			String message = "hello word!";
			channel.basicPublish("", default_queue_name, null, message.getBytes());
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
