package org.spring.boot.rabbitmq.subscribe;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.spring.boot.rabbitmq.util.RabbitmqUtils;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Consumer {

	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = null;
		connection = RabbitmqUtils.getConnection();
		Channel channel = connection.createChannel();
		channel.basicQos(128);
		DefaultConsumer consumer = new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				// TODO Auto-generated method stub
				String msg = String.format("consumerTag={%s},message={%s}", consumerTag, new String(body, "UTF-8"));
				System.out.println(msg);
			}

		};
		channel.basicConsume(Producer.QUEUE_NAME1, consumer);
		channel.basicConsume(Producer.QUEUE_NAME2, consumer);

	}

}
