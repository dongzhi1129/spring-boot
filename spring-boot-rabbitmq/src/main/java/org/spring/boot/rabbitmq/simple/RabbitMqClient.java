package org.spring.boot.rabbitmq.simple;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.spring.boot.rabbitmq.util.RabbitmqUtils;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class RabbitMqClient {
	
	public static void main(String[] args) {
		Connection connection = null;
		try {
			connection = RabbitmqUtils.getConnection();
			final Channel channel = connection.createChannel();
			
			Consumer consumer = new DefaultConsumer(channel) {

				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
						byte[] body) throws IOException {
					// TODO Auto-generated method stub
					System.out.println("Receive message:" + new String(body));
					channel.basicAck(envelope.getDeliveryTag(), true);
				}
				
			};
			channel.basicConsume(RabbitMqServer.default_queue_name, consumer);
		} catch (IOException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
