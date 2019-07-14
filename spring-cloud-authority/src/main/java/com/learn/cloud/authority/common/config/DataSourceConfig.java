package com.learn.cloud.authority.common.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.learn.cloud.authority.common.core.BusinessException;

@Configuration
@MapperScan(basePackages = "com.learn.cloud.authority.mapper")
public class DataSourceConfig {

	@Bean
	public PlatformTransactionManager platformTransactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public TransactionInterceptor txAdvice(DataSource dataSource) {

		NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
		TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
		transactionInterceptor.setTransactionManager(platformTransactionManager(dataSource));
		RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
		readOnlyTx.setReadOnly(false);
		readOnlyTx.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		readOnlyTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(BusinessException.class)));
		readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		Map<String, TransactionAttribute> nameMap = new HashMap<>();
		nameMap.put("add*", readOnlyTx);
		nameMap.put("save*", readOnlyTx);
		nameMap.put("insert*", readOnlyTx);
		nameMap.put("create*", readOnlyTx);
		nameMap.put("update*", readOnlyTx);
		nameMap.put("edit*", readOnlyTx);
		nameMap.put("delete*", readOnlyTx);
		nameMap.put("remove*", readOnlyTx);
		nameMap.put("process*", readOnlyTx);
		source.setNameMap(nameMap);
		transactionInterceptor.setTransactionAttributeSources(source);
		return transactionInterceptor;
	}

	@Bean
	public Advisor txAdviceAdvisor(DataSource dataSource) {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(* com.learn.cloud.authority.service.impl..*.*(..))");
		return new DefaultPointcutAdvisor(pointcut, txAdvice(dataSource));
	}
}
