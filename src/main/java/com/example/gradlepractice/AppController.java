package com.example.gradlepractice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AppController {

    private final AppConfiguration appConfiguration;

    @GetMapping(path = "config")
    public String getProperty() {
        return appConfiguration.getArgument();
    }

    @GetMapping(path = "importArg")
    public String getImportArgs() {
        return appConfiguration.getImportArg();
    }
}
