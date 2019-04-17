package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    private final TemplateEngine templateEngine;

    public HomeController(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @GetMapping
    public String home(){

        return templateEngine.template("index.ftl", null);
    }
}
