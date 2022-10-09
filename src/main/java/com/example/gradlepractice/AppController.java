package com.example.gradlepractice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    AppConfiguration appConfiguration;

    @GetMapping(path = "config")
    public String getProperty() {
        return appConfiguration.getArgument();
    }
}
