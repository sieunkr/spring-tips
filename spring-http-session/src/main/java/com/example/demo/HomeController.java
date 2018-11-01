package com.example.demo;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping("/home")
    public String home(HttpSession httpSession){

        System.out.println(httpSession.getId());
    
        return "home";
    }

}
