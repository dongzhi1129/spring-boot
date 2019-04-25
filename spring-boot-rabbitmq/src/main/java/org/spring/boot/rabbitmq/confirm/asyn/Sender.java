package org.spring.boot.rabbitmq.confirm.asyn;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

import org.spring.boot.rabbitmq.util.RabbitmqUtils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

/**
 * 异步确认模式
 * 
 *
 */
public class Sender {

	public static String ASYNC_CONFIRM_QUEUE = "async_confirm_queue";
	
	public static SortedSet<Long> unackDeliverflag = Collections.synchronizedSortedSet(new TreeSet<Long>());

	public static void main(String[] args) throws IOException, TimeoutException {
		
		Connection connection = RabbitmqUtils.getConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(ASYNC_CONFIRM_QUEUE, false, false, false, null);
		channel.confirmSelect();
		channel.addConfirmListener((deliveryTag, multiple) -> {
			if (multiple) {
				unackDeliverflag.headSet(deliveryTag+1).clear();
				System.out.println("multips====>success");
			} else {
				unackDeliverflag.remove(deliveryTag);
				System.out.println("no multips====>success");
			}
		}, (deliveryTag, multiple) -> {
			if (multiple) {
				unackDeliverflag.headSet(deliveryTag+1).clear();
				System.out.println("multips====>failed");
			} else {
				unackDeliverflag.remove(deliveryTag);
				System.out.println("no multips====>no success");
			}
		});

		while(true) {
			Long deliverFlag = channel.getNextPublishSeqNo();
			channel.basicPublish("", ASYNC_CONFIRM_QUEUE, null, deliverFlag.toString().getBytes());
			unackDeliverflag.add(deliverFlag);
		}
	}

}
