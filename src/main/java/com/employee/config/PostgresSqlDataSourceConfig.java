package com.employee.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Profile("multi_db")
@Configuration
public class PostgresSqlDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.postgres")
    public DataSourceProperties postgresSqldataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource postgresSqlDataSource() {
        return postgresSqldataSourceProperties().initializeDataSourceBuilder().build();
    }
}
