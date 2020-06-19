package com.example.demo.config.fatpc;

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
		entityManagerFactoryRef = "fatPcAccountingEntityManagerFactory", basePackages = {
		"jp.co.ntt.common.repository.fatpc.accounting" })
public class FatPcAccountingDbConfig {

	@Bean(name = "fatPcAccountingDataSourceProperties")
	@ConfigurationProperties("spring.fatpc-accounting-datasource")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "fatPcAccountingDataSource")
	@ConfigurationProperties("spring.fatpc-accounting-datasource.configuration")
	public HikariDataSource dataSource(@Qualifier("fatPcAccountingDataSourceProperties") DataSourceProperties dataSourceProperties) {
		return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class)
				.build();
	}

	@Bean(name = "fatPcAccountingEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("fatPcAccountingDataSource") DataSource dataSource) {
		return builder
				.dataSource(dataSource)
				.packages("jp.co.ntt.common.entity")
				.persistenceUnit("fatpc_accounting")
				.build();
	}
}
