package org.spring.boot.rabbitmq.tx;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.spring.boot.rabbitmq.util.RabbitmqUtils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class Consumer {
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = RabbitmqUtils.getConnection();
		final Channel channel = connection.createChannel();
		DefaultConsumer consumer = new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
					byte[] body) throws IOException {
				// TODO Auto-generated method stub
				System.out.println("Receive message:" + new String(body));
				channel.basicAck(envelope.getDeliveryTag(), false);
			}
			
		};
		channel.basicConsume("tx_queue1", consumer);
	}
	

}
