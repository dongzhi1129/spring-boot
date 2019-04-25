package org.spring.boot.rabbitmq.tx;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.spring.boot.rabbitmq.util.RabbitmqUtils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer {
	
	public static void main(String[] args) throws IOException, TimeoutException {
		
		Connection connection = RabbitmqUtils.getConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare("tx_queue1", false, false, false, null);
		channel.queueBind("tx_queue", "", "");
		
		channel.txSelect();
		try {
			for(int i=0;i<10;i++) {
				channel.basicPublish("", "", null, "hello word!".getBytes());
			}
			channel.txCommit();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			channel.txRollback();
		}
	}

}
