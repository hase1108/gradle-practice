package com.example.gradlepractice;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ConditionalOnProperty(name = "service.mock", havingValue = "true")
@RestController
public class ProfileController {


    @GetMapping(path = "profile")
    public String getProfile() {
        return "activate";
    }
}
