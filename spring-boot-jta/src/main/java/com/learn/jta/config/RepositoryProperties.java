package com.learn.jta.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "spring.datasource.repository")
@Getter
@Setter
public class RepositoryProperties {

	private String url;

	private String driverClassName;

	private String userName;

	private String password;

}
