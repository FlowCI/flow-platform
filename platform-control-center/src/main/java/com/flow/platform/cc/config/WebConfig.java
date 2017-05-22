package com.flow.platform.cc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by gy@fir.im on 17/05/2017.
 * Copyright fir.im
 */
@Configuration
@EnableWebMvc
@ComponentScan({
        "com.flow.platform.cc.controller",
        "com.flow.platform.cc.service",
        "com.flow.platform.cc.dao"})
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final String SYSTEM_FLOW_CC_ENV = "FLOW_CC_ENV";

    private static final String SPRING_ENV = "spring.profiles.active";
    private static final String CC_ENV = "flow.cc.env";
    private static final String DEFAULT_CC_ENV = "local";

    private static final int ASYNC_POOL_SIZE = 100;

    @Bean
    public PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        String envPropertiesFile = String.format("app-%s.properties", currentEnv());
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource(envPropertiesFile));
        return configurer;
    }

    @Bean
    public String currentEnv() {
        String env = System.getProperty(SPRING_ENV);
        if (env == null) {
            env = System.getenv(SYSTEM_FLOW_CC_ENV);
            if (env == null) {
                env = System.getProperty(CC_ENV, DEFAULT_CC_ENV);
            }
        }
        return env;
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(ASYNC_POOL_SIZE, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = Executors.defaultThreadFactory().newThread(r);
                t.setDaemon(true);
                return t;
            }
        });
    }
}
