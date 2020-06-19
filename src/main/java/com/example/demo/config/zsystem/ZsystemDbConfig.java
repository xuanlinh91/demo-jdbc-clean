package com.example.demo.config.zsystem;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
		entityManagerFactoryRef = "zsystemEntityManagerFactory", basePackages = {
		"jp.co.ntt.common.repository.zsystem"})
public class ZsystemDbConfig {
	@Primary
	@Bean(name = "zsystemDataSourceProperties")
	@ConfigurationProperties("spring.zsystem-datasource")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	@Primary
	@Bean(name = "zsystemDataSource")
	@ConfigurationProperties("spring.zsystem-datasource.configuration")
	public HikariDataSource dataSource(@Qualifier("zsystemDataSourceProperties") DataSourceProperties dataSourceProperties) {
		return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class)
				.build();
	}

	@Primary
	@Bean(name = "zsystemEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			EntityManagerFactoryBuilder builder, @Qualifier("zsystemDataSource") DataSource dataSource) {
		return builder
				.dataSource(dataSource)
				.packages("jp.co.ntt.common.entity")
				.persistenceUnit("zsystem")
				.build();
	}
}
