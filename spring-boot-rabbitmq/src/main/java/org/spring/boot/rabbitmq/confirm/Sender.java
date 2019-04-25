package org.spring.boot.rabbitmq.confirm;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.spring.boot.rabbitmq.util.RabbitmqUtils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 
 * 1.普通模式,单条确认 2.批量确认，如果其中一条失败，则都会失败3异步
 *
 */
public class Sender {

	public static String DEFAULT_CONFIRM_QUEUE = "default_confirm_queue";

	public static void main(String[] args) throws Exception {
		Connection connection = RabbitmqUtils.getConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(DEFAULT_CONFIRM_QUEUE, false, false, false, null);
		// 如果之前设置过事务模式，是不允许再设置confirm模式
		channel.confirmSelect();
		for (int i = 0; i < 10; i++) {
			channel.basicPublish("", DEFAULT_CONFIRM_QUEUE, null, "hello confirm queue!".getBytes());
		}
		if (!channel.waitForConfirms()) {
			System.out.println("send failed");
		} else {
			System.out.println("success");
		}
		RabbitmqUtils.closeQuietly(connection);
	}

}
