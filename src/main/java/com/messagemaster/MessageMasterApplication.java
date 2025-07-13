package com.messagemaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class MessageMasterApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MessageMasterApplication.class);
        app.addListeners(new ApplicationPidFileWriter()); // Для контроля процесса
        app.run(args);

        // Добавляем явное завершение приложения
        System.exit(0);
    }
}