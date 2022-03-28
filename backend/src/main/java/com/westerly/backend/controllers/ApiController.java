package com.westerly.backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @GetMapping("/data")
    public String home() {
        return "data from rest api";
    }
}
