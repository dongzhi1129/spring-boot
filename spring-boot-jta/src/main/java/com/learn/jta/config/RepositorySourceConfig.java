package com.learn.jta.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;

@Configuration
@MapperScan(basePackages = "com.learn.jta.repository.mapper", sqlSessionFactoryRef = RepositorySourceConfig.DEFUALT_REPOSITORY_SQLSESSIONFACTROY_BEAN_NAME)
public class RepositorySourceConfig {

	public static final String DEAULT_REPOSITORY_DATASORUCE_BEAN_NAME = "RepositoryDataSource";

	public static final String DEFUALT_REPOSITORY_SQLSESSIONFACTROY_BEAN_NAME = "RepositorySqlSessionFactoryBean";

	@Bean(DEAULT_REPOSITORY_DATASORUCE_BEAN_NAME)
	public DataSource dataSource(RepositoryProperties repositoryProperties) {
		MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
		mysqlXADataSource.setUrl(repositoryProperties.getUrl());
		mysqlXADataSource.setUser(repositoryProperties.getUserName());
		mysqlXADataSource.setPassword(repositoryProperties.getPassword());

		AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
		atomikosDataSourceBean.setUniqueResourceName(DEAULT_REPOSITORY_DATASORUCE_BEAN_NAME);
		atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
		return atomikosDataSourceBean;
	}

	@Bean(DEFUALT_REPOSITORY_SQLSESSIONFACTROY_BEAN_NAME)
	public SqlSessionFactory sqlSessionFactoryBean(@Qualifier(DEAULT_REPOSITORY_DATASORUCE_BEAN_NAME) DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		return sqlSessionFactoryBean.getObject();
	}

}
