package com.learn.jta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.learn.jta.config.BillProperties;
import com.learn.jta.config.RepositoryProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {BillProperties.class,RepositoryProperties.class})
public class SpringBootJtaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJtaApplication.class, args);
	}

}
