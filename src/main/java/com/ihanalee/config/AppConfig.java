package com.ihanalee.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Hana Lee
 * @since 2016-06-22 18:26
 */
@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = {"com.ihanalee"})
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class AppConfig {
}