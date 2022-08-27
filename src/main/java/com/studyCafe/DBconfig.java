package com.studyCafe;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DBconfig {

    private static final String DEFAULT_NAMING_STRATEGY
            = "org.springframework.boot.orm.jpa.hibernate.SpringNamingStrategy";

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource.member")
    public DataSource memberDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder) {

        Map<String, String> propertiesHashMap = new HashMap<>();
        propertiesHashMap.put("hibernate.ejb.naming_strategy",DEFAULT_NAMING_STRATEGY);

        return builder.dataSource(memberDataSource())
                .packages("com.studyCafe.domain.Member")
                .properties(propertiesHashMap)
                .build();
    }

    @Primary
    @Bean(name = "transactionManager")
    PlatformTransactionManager transactionManager(
            EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }

    @Configuration
    @EnableJpaRepositories(
            basePackages="com.studyCafe.repository",
            entityManagerFactoryRef = "entityManagerFactory",
            transactionManagerRef = "transactionManager")
    static class DbArticleJpaRepositoriesConfig {
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource.seat")
    public DataSource userDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "entityManagerFactorySeat")
    public LocalContainerEntityManagerFactoryBean userEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {

        return builder.dataSource(userDataSource())
                .packages("com.studyCafe.domain.Seat")
                .build();
    }

    @Bean(name = "transactionManagerSeat")
    @Primary
    PlatformTransactionManager userTransactionManagerMain(
            EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(userEntityManagerFactory(builder).getObject());
    }

    @Configuration
    @EnableJpaRepositories(
            basePackages="com.studyCafe.repository",
            entityManagerFactoryRef = "entityManagerFactorySeat",
            transactionManagerRef = "transactionManagerSeat")
    static class DbUserJpaRepositoriesConfig {
    }
}
