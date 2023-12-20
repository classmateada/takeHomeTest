package com.classmateada.takehometest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/")
    public String helloWorld() {
        return "Hello world (from port: " + port + ")";
    }
}
