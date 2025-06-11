package com.employee.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("multi_db")
public class MySqlDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.mysql")
    public DataSourceProperties mySqldataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource mysqlDataSource() {
        return mySqldataSourceProperties().initializeDataSourceBuilder().build();
    }
}
