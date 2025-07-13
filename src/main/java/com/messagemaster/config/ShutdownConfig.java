package com.messagemaster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

@Configuration
public class ShutdownConfig {

    @Bean
    public ExitCodeGenerator exitCodeGenerator() {
        return () -> 0;
    }

    @Bean
    public GracefulShutdown gracefulShutdown() {
        return new GracefulShutdown();
    }

    public static class GracefulShutdown {
        public void shutdown(ApplicationContext context) {
            SpringApplication.exit(context, context.getBean(ExitCodeGenerator.class));
        }
    }
}