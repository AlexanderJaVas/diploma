package com.example.diploma.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    @GetMapping("/authentication")
    public String login() {
        return "security/authentication";
    }
}
