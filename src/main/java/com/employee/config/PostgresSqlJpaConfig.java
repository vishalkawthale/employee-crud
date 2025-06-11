package com.employee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableJpaRepositories(basePackages = "com.employee.repository.sql.postgres"
        , entityManagerFactoryRef = "postgresSqlEntityManagerFactory",
        transactionManagerRef = "postgresSqlTransactionManager")
@Profile("multi_db")
public class PostgresSqlJpaConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean postgresSqlEntityManagerFactory(@Qualifier(value = "postgresSqlDataSource") DataSource dataSource) {
        Map<String, String> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        //jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        jpaProperties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
        jpaProperties.put("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));

        return getLocalContainerEntityManagerFactoryBean(dataSource, jpaProperties, "com.employee.entity.sql.postgres");
    }

    private static LocalContainerEntityManagerFactoryBean getLocalContainerEntityManagerFactoryBean(DataSource dataSource, Map<String, String> jpaProperties, String packagesToScan) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan(packagesToScan);
        //entityManagerFactoryBean.setPersistenceUnitName("postgres");
        entityManagerFactoryBean.setJpaVendorAdapter(new org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setJpaPropertyMap(jpaProperties);
        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager postgresSqlTransactionManager(@Qualifier(value = "postgresSqlEntityManagerFactory") LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(mysqlEntityManagerFactory.getObject()));
    }
}
