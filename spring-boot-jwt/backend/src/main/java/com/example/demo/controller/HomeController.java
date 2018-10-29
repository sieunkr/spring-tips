package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {

    @GetMapping
    public ResponseEntity getHome() {
        return ResponseEntity.ok("home");
    }

    @GetMapping
    @RequestMapping("/secured")
    public ResponseEntity getSecured(Principal principal) {
        return ResponseEntity.ok("Secured");
    }
}
