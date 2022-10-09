package com.example.gradlepractice;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "app.config")
public class AppConfiguration {

    private final String argument;
}
