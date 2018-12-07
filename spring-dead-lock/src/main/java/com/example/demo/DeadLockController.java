package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deadlock")
public class DeadLockController {

    private final Object left = new Object();
    private final Object right = new Object();

    @GetMapping("/left")
    public String leftRight() throws InterruptedException {
        synchronized (left) {
            Thread.sleep(5000);
            synchronized (right) {
                System.out.println("left - right");
            }
        }
        return "ok";
    }

    @GetMapping("/right")
    public String rightLeft() throws InterruptedException {
        synchronized (right) {
            Thread.sleep(5000);
            synchronized (left) {
                System.out.println("right - left");
            }
        }
        return "ok";
    }





}
