package com.learn.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

	private RedisConnectionFactory redisConnectionFactory;

	@Bean("redisConnectionFactory")
	public RedisConnectionFactory initRedisConnectionFactory() {
		if (redisConnectionFactory != null) {
			return this.redisConnectionFactory;
		}
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(50);
		jedisPoolConfig.setMaxTotal(100);
		jedisPoolConfig.setMaxWaitMillis(1000);
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory(jedisPoolConfig);
		RedisStandaloneConfiguration standaloneConfiguration = connectionFactory.getStandaloneConfiguration();
		standaloneConfiguration.setHostName("192.168.199.118");
		standaloneConfiguration.setPort(6739);
		this.redisConnectionFactory = connectionFactory;
		return connectionFactory;
	}

	@Bean("redisTemplate")
	public RedisTemplate<Object, Object> initRedistemplate() {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
		redisTemplate.setConnectionFactory(initRedisConnectionFactory());
		return redisTemplate;
	}

}
