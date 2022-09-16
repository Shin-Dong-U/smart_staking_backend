package com.yoyohub.staking.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;


@PropertySource("classpath:${spring.profiles.active}/db.properties")
@Configuration
public class DbConfiguration {

    @Value("${db.driver-class-name}") private String driverClassName;
    @Value("${db.url}") private String url;
    @Value("${db.user}") private String user;
    @Value("${db.password}") private String password;
    @Value("${db.maximum-pool-size}") private int maxPoolSize;

    @PostConstruct
    public HikariConfig hikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);
        config.setMaximumPoolSize(maxPoolSize);

        return config;
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }
}