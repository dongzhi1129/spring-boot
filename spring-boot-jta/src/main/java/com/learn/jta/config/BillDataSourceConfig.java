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
@MapperScan(basePackages = "com.learn.jta.bill.mapper", sqlSessionFactoryRef = BillDataSourceConfig.DEFUALT_BILL_SQLSESSIONFACTROY_BEAN_NAME)
public class BillDataSourceConfig {

	public static final String DEAULT_BILL_DATASORUCE_BEAN_NAME = "BillDataSource";

	public static final String DEFUALT_BILL_SQLSESSIONFACTROY_BEAN_NAME = "BillSqlSessionFactoryBean";

	@Bean(DEAULT_BILL_DATASORUCE_BEAN_NAME)
	public DataSource dataSource(BillProperties billProperties) {
		MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
		mysqlXADataSource.setUrl(billProperties.getUrl());
		mysqlXADataSource.setUser(billProperties.getUsername());
		mysqlXADataSource.setPassword(billProperties.getPassword());

		AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
		atomikosDataSourceBean.setUniqueResourceName(DEAULT_BILL_DATASORUCE_BEAN_NAME);
		atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
		return atomikosDataSourceBean;
	}

	@Bean(DEFUALT_BILL_SQLSESSIONFACTROY_BEAN_NAME)
	public SqlSessionFactory sqlSessionFactoryBean(@Qualifier(DEAULT_BILL_DATASORUCE_BEAN_NAME) DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		return sqlSessionFactoryBean.getObject();
	}

}
