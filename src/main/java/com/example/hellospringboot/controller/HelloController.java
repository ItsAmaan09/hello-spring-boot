package com.example.hellospringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Spring boot!";
    }

    @GetMapping("/greet")
    public String greetUser(@RequestParam  String name) {
        return "Hello " + name;
    }
}
