package com.example.demo.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/test")
@RestController
public class MainControllers {

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/customer")
    public String testCustome(){
        return "HELLO CUSTOMER";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String testAdmin(){
        return "HELLO ADMIN";
    }
}