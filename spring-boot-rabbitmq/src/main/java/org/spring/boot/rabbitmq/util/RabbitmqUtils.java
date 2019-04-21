package org.spring.boot.rabbitmq.util;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitmqUtils {

	private static final String ip = "192.168.199.118";

	private static final int port = 5672;

	public static final String user = "dongzhi.wang";

	public static final String password = "admin";

	private static ConnectionFactory connectionFactory;

	private static synchronized ConnectionFactory getConnectionFactory() {
		if (connectionFactory == null) {
			ConnectionFactory connectionFactory = new ConnectionFactory();
			connectionFactory.setHost(ip);
			connectionFactory.setPort(port);
			connectionFactory.setVirtualHost("/practice");
			connectionFactory.setUsername(user);
			connectionFactory.setPassword(password);
			RabbitmqUtils.connectionFactory = connectionFactory;
		}
		return connectionFactory;

	}

	public static Connection getConnection() throws IOException, TimeoutException {
		ConnectionFactory connectionFactory = RabbitmqUtils.getConnectionFactory();
		return connectionFactory.newConnection();
	}

	public static void closeQuietly(final Closeable closeable) {

		try {
			closeable.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void closeQuietly(final AutoCloseable closeable) {

		try {
			closeable.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
