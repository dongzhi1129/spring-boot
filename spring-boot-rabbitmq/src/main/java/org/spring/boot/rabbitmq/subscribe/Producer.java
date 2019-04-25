package org.spring.boot.rabbitmq.subscribe;

import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.spring.boot.rabbitmq.util.RabbitmqUtils;

import com.rabbitmq.client.AMQP.Exchange;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer {
	
	public static String EXCHANGE_NAME = "defaut_subscribe_exchange";
	
	public static String QUEUE_NAME1 = "default_subscribe_queue1";
	
	public static String QUEUE_NAME2 = "default_subscribe_queue2";
	
	public static void main(String[] args) {
		Connection connection = null;
		Channel channel = null;
		try {
			connection = RabbitmqUtils.getConnection();
			channel = connection.createChannel();
			channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, false);
			channel.queueDeclare(QUEUE_NAME1, false, false, false, null);
			channel.queueDeclare(QUEUE_NAME2, false, false, false, null);
			channel.queueBind(QUEUE_NAME1, EXCHANGE_NAME, QUEUE_NAME1);
			channel.queueBind(QUEUE_NAME2, EXCHANGE_NAME, QUEUE_NAME2);
			while(true) {
				channel.basicPublish(EXCHANGE_NAME, QUEUE_NAME1, null, "hello1".getBytes());
				channel.basicPublish(EXCHANGE_NAME, QUEUE_NAME2, null, "hello2".getBytes());
				TimeUnit.SECONDS.sleep(2);
			}
		} catch (IOException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			RabbitmqUtils.closeQuietly(connection);
		}
	}

}
