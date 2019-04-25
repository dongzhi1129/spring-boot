package org.spring.boot.rabbitmq.work;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.spring.boot.rabbitmq.util.RabbitmqUtils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {
	
	public static final String DEFAULT_WORK_QUEUE = "test_work_queue";
	
	public static void main(String[] args) {
		Connection connection = null;
		Channel channel = null;
		try {
			connection = RabbitmqUtils.getConnection();
			channel = connection.createChannel();
			//不允许改变已经声明队列的参数
			channel.queueDeclare(DEFAULT_WORK_QUEUE, false, false, false, null);
			while(true) {
				String message = String.format("hello Workd->[{%s}]", System.currentTimeMillis());
				System.out.println("Send [" + message + "] to queue.");
				channel.basicPublish("", DEFAULT_WORK_QUEUE, null, message.getBytes());
				TimeUnit.SECONDS.sleep(1);
			}
			
			
		} catch (IOException | TimeoutException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			RabbitmqUtils.closeQuietly(channel);
			RabbitmqUtils.closeQuietly(connection);
		}
	}
}
