package com.messagemaster.controller;

import com.messagemaster.config.ShutdownConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final ApplicationContext context;
    private final ShutdownConfig.GracefulShutdown gracefulShutdown;

    public AdminController(ApplicationContext context,
                           ShutdownConfig.GracefulShutdown gracefulShutdown) {
        this.context = context;
        this.gracefulShutdown = gracefulShutdown;
    }

    @PostMapping("/shutdown")
    public void shutdown() {
        gracefulShutdown.shutdown(context);
    }
}