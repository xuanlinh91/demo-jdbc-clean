package com.example.demo.config.log;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
		entityManagerFactoryRef = "logDbEntityManagerFactory", basePackages = {
		"jp.co.ntt.common.repository.logdb" })
public class LogDbConfig {

	@Bean(name = "logDbDataSourceProperties")
	@ConfigurationProperties("spring.logdb-datasource")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "logDbDataSource")
	@ConfigurationProperties("spring.fatpc-datasource.configuration")
	public HikariDataSource dataSource(@Qualifier("logDbDataSourceProperties") DataSourceProperties dataSourceProperties) {
		return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class)
				.build();
	}

	@Bean(name = "logDbEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("logDbDataSource") DataSource dataSource) {
		return builder
				.dataSource(dataSource)
				.packages("jp.co.ntt.common.entity")
				.persistenceUnit("logdb")
				.build();
	}
}
